/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.chromedev.messageshandlers;

import com.smeup.dsl.debugger.chromedev.DebuggeeEventDispatcher;

/**
 * 
 * @author marco.lanari
 */
@FunctionalInterface
public interface DebuggeeMessageSender {
    
    public void send(DebuggeeEventDispatcher dispatcher);
    
}
