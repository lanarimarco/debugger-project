/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.endpoint;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.smeup.debugger.chromedev.Debuggee;
import com.smeup.debugger.chromedev.DebuggeeInstancesFactory;
import com.smeup.debugger.chromedev.messageshandlers.DebuggeeMessageSender;
import com.smeup.debugger.chromedev.model.Result;
import com.smeup.debugger.chromedev.model.VoidResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author marco.lanari
 */
@ServerEndpoint(value="/debug/{debuggeeId}/{scriptId}")
public class DebuggerEndpoint {

    private final Map<String, DebuggeeWrapper> debuggees = new HashMap<>();
    private static final Logger LOGGER = java.util.logging.Logger.getLogger(DebuggerEndpoint.class.getName());
    private final MessageHandlerInvoker invoker = new MessageHandlerInvoker();

    @OnOpen
    public void onOpen(@PathParam(value = "debuggeeId") String debuggeeId, 
            @PathParam(value = "scriptId") String scriptId, Session session, EndpointConfig config) {
        LOGGER.log(Level.INFO, "Debugger open session. Source: {0}, sessionId: {1}", 
                new Object[]{scriptId, session.getId()});
        try {
            DebuggeeInstancesFactory factory = DebuggerContextListener.DEBUGGEE_INSTANCE_FACTORY;
            Debuggee debuggee =  factory.createInstanceBy(debuggeeId);
            debuggees.put(session.getId(), new DebuggeeWrapper(debuggee, scriptId));
        } catch (Throwable e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            debuggees.remove(session.getId());
            try {
                session.close(new CloseReason(CloseCodes.UNEXPECTED_CONDITION, e.getMessage()));
                
            } //mi frega nulla se exception su close
            catch (IOException e2) {
            };
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        Gson gson = new Gson();
        try {
            LOGGER.log(Level.FINE, "Chrome: {0}", message);
            DebuggeeWrapper debuggee = debuggees.get(session.getId());
            if (debuggee == null) {
                throw new IllegalArgumentException("not found " + session.getId());
            } else {
                JsonElement parsed = new JsonParser().parse(message);
                int id = parsed.getAsJsonObject().get("id").getAsInt();
                String method = parsed.getAsJsonObject().get("method").getAsString();
                JsonElement params = parsed.getAsJsonObject().get("params");
                GenericMessage genericMessage = new GenericMessage(id, method, params);

                Result result = invoker.invoke(debuggee, genericMessage);
                if (result == null) {
                    result = new VoidResult(genericMessage.getId());
                }
                if (result.getResult() == null) {
                    result.setResult("{}");
                }
                String jsonResult = gson.toJson(result);
                LOGGER.log(Level.FINE, "Debuge: {0}", jsonResult);
                session.getBasicRemote().sendText(jsonResult);
                DebuggeeMessageSender sender = result.getDebuggeeMessageSender();
                if (sender != null) {
                    sender.send(new DebuggeeEventDispatrcherImpl(session));
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            debuggees.remove(session.getId());
            try {
                session.close(new CloseReason(CloseCodes.UNEXPECTED_CONDITION, e.getMessage()));
            } //mi frega nulla se exception su close
            catch (IOException e2) {
            };
        }
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.log(Level.INFO, "Chrome: close sessionId: {0}", session.getId());
        debuggees.remove(session.getId());
    }

}
