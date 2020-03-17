/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.internal.truffleproxy;

import javax.websocket.CloseReason;
import javax.websocket.Session;

/**
 *
 * @author marco.lanari
 */
public interface TruffleCallbackHandler {
    
    public void truffleOpenedSession(Session truffleSession);
    
    public void truffleClosedSession(Session truffleSession, CloseReason reason);
    
    public void truffleSentMessage(String message);
    
}
