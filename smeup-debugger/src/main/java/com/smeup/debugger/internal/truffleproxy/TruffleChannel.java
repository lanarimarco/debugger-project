/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.internal.truffleproxy;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * Rappresenta il canale websocket aperto con truffle
 * @author marco.lanari
 */
@ClientEndpoint
public class TruffleChannel {
    
    
    private final URI endpointURI;
    private final TruffleCallbackHandler truffleCallbackHandler;
    
    //la sessione websocket aperta con truffle
    private Session userSession = null;
    
    private static final Logger LOGGER = Logger.getLogger(TruffleProxy.class.getName());

    TruffleChannel(URI endpointURI, TruffleCallbackHandler truffleCallbackHandler) {
        try {
            this.endpointURI = endpointURI;
            this.truffleCallbackHandler = truffleCallbackHandler;        

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    void connect() throws DeploymentException, IOException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, endpointURI);
    }

    /**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        LOGGER.log(Level.INFO, "Truffle websocket connection opened");
        this.userSession = userSession;
        truffleCallbackHandler.truffleOpenedSession(userSession);
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        LOGGER.log(Level.INFO, "Truffle websocket connection closed");
        this.userSession = null;
        truffleCallbackHandler.truffleClosedSession(userSession, reason);
        
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        truffleCallbackHandler.truffleSentMessage(message);
    }
    
  
    /**
     * Send a message.
     *
     * @param message
     */
    void sendMessage(String message) throws IOException{
        this.userSession.getBasicRemote().sendText(message);
    }

    void close(CloseReason reason) {
        try {
            this.userSession.close(reason);
        }
        catch(IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

 
}
