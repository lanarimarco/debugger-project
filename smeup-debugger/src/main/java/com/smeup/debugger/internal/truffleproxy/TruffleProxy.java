/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.internal.truffleproxy;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
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
@ServerEndpoint(value = "/truffle/{truffleSessionId}")
public class TruffleProxy {

    private final Map<String, TruffleChannel> channels
            = new HashMap<String, TruffleChannel>();

    private static final String TRUFFLE_BASE_URI = "ws://localhost:9229";

    private static final Logger LOGGER = Logger.getLogger(TruffleProxy.class.getName());
    
    private static final LoggingHandler LOGGING_HANDLER = new LoggingHandler();
  
    static {
        LOGGER.addHandler(LOGGING_HANDLER);
    }

    private class CallbackHandler implements TruffleCallbackHandler {

        private final String truffleSessionId;
        private final Session chromeSession;

        private CallbackHandler(String truffleSessionId, Session chromeSession) {
            this.truffleSessionId = truffleSessionId;
            this.chromeSession = chromeSession;
        }

        public void truffleOpenedSession(Session truffleSession) {
            LOGGER.log(Level.INFO, "Truffle opened session");
        }

        public void truffleClosedSession(Session truffleSession, CloseReason reason) {
            try {
                chromeSession.close(reason);
                channels.remove(truffleSessionId);
                LOGGING_HANDLER.endLoggingSession();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        public void truffleSentMessage(String message) {
            try {
                LOGGER.log(Level.INFO, "Truffle:{0}", message);
                chromeSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }

    }

    @OnOpen
    public void chromeOpenedSession(@PathParam(value = "truffleSessionId") String truffleSessionId, Session chromeSession) {
        try {
            LOGGING_HANDLER.startLoggingSession(truffleSessionId);
            LOGGER.log(Level.INFO, "Chrome require debugging for {0}, creating TruffleChannel", truffleSessionId);
            TruffleChannel truffleChannel;
            channels.put(truffleSessionId, truffleChannel = new TruffleChannel(createURI(truffleSessionId),
                    new CallbackHandler(truffleSessionId, chromeSession)));
            truffleChannel.connect();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @OnMessage
    public void chromeSentMessage(@PathParam(value = "truffleSessionId") String truffleSessionId, String message, Session session) {
        LOGGER.log(Level.INFO, "Chrome :{1}", new Object[]{truffleSessionId, message});
        try {
            TruffleChannel channel = channels.get(truffleSessionId);
            if (channel != null) {
                channel.sendMessage(message);
            } else {
                throw new IllegalArgumentException("Not found truffleSessionId: " + truffleSessionId);
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @OnClose
    public void chromeClosedSession(@PathParam(value = "truffleSessionId") String truffleSessionId, Session session) {
        TruffleChannel channel = channels.get(truffleSessionId);
        if (channel != null) {
            channel.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Chrome closed session"));
            channels.remove(truffleSessionId);
            LOGGING_HANDLER.endLoggingSession();
        }
    }

    private URI createURI(String truffleSessionId) throws URISyntaxException {
        return new URI(TRUFFLE_BASE_URI + "/" + truffleSessionId);
    }
}
