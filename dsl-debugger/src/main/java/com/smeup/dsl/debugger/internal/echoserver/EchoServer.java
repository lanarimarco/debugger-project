/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.internal.echoserver;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author marco.lanari
 */
@ServerEndpoint("/echo")
public class EchoServer {
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " ha aperto una connessione"); 
        try {
            session.getBasicRemote().sendText("Connessione Stabilita!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @OnMessage
    public void onMessage(String message, Session session)  {
        System.out.println("Ricevuto messaggio da: " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @OnClose
    public void onClose(Session session) {
        System.out.println("Session " +session.getId()+" terminata");
    }
    
}
