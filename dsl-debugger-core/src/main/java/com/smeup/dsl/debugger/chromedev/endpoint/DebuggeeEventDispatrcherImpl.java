/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.chromedev.endpoint;

import com.google.gson.Gson;
import com.smeup.dsl.debugger.chromedev.DebuggeeEventDispatcher;
import com.smeup.dsl.debugger.chromedev.model.methods.DebuggerPaused;
import com.smeup.dsl.debugger.chromedev.model.methods.DebuggerResumed;
import com.smeup.dsl.debugger.chromedev.model.methods.DebuggerScriptParsed;
import com.smeup.dsl.debugger.chromedev.model.methods.RuntimeExecutionContextCreated;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.Session;

/**
 *
 * @author marco.lanari
 */
class DebuggeeEventDispatrcherImpl implements DebuggeeEventDispatcher{
    
    private final Session session;
    private static final Logger LOGGER = Logger.getLogger(DebuggeeEventDispatrcherImpl.class.getName());
    
    public DebuggeeEventDispatrcherImpl(Session session) {
        this.session = session;
    }

    @Override
    public void notifyRuntimeExecutionContextCreated(RuntimeExecutionContextCreated runtimeExecutionContextCreated) {
        try {
            String json = new Gson().toJson(runtimeExecutionContextCreated);
            session.getBasicRemote().sendText(json);
            LOGGER.log(Level.FINE, "Debuggee: {0}", json);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void notifyDebuggerScriptParsed(DebuggerScriptParsed debuggerScriptParsed) {
        try {
            String json = new Gson().toJson(debuggerScriptParsed);
            session.getBasicRemote().sendText(json);
            LOGGER.log(Level.FINE, "Debuggee: {0}", json);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void notifyDebuggerPaused(DebuggerPaused debuggerPaused) {
        try {
            String json = new Gson().toJson(debuggerPaused);
            session.getBasicRemote().sendText(json);
            LOGGER.log(Level.FINE, "Debuggee: {0}", json);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void notifyDebuggerResumed(DebuggerResumed debuggerResumed) {
        try {
            String json = new Gson().toJson(debuggerResumed);
            session.getBasicRemote().sendText(json);
            LOGGER.log(Level.FINE, "Debuggee: {0}", json);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void notifyCloseSession(String reason) {
        try {
            LOGGER.log(Level.FINE, "Debuggee: {0}", reason);
            CloseReason closeReason = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, reason);
            session.close(closeReason);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
}
