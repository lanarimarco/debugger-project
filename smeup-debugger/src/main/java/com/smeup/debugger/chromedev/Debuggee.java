/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev;

import com.smeup.debugger.chromedev.messageshandlers.Debugger;
import com.smeup.debugger.chromedev.messageshandlers.Profiler;
import com.smeup.debugger.chromedev.messageshandlers.Runtime;

/**
 * A debuggee module
 * @author marco.lanari
 */
public interface Debuggee<I> {
    
    public I createInterpreter(String scriptId);
    public Debugger createDebugger(I interpeter);
    public Profiler createProfiler(I interpreter);
    public Runtime createRuntime(I interpreter);

    
    
    
}
