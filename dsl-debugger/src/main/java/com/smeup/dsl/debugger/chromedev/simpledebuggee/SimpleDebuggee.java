/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.chromedev.simpledebuggee;

import com.smeup.dsl.debugger.chromedev.messageshandlers.Debugger;
import com.smeup.dsl.debugger.chromedev.messageshandlers.Profiler;
import com.smeup.dsl.debugger.chromedev.messageshandlers.Runtime;
import com.smeup.dsl.debugger.chromedev.Debuggee;

/**
 *
 * @author marco.lanari
 */
public abstract class SimpleDebuggee implements Debuggee<SimpleInterpreter>{
    
    @Override
    public Debugger createDebugger(SimpleInterpreter interpreter) {
        return new SimpleDebugger(interpreter);
    }

    @Override
    public Profiler createProfiler(SimpleInterpreter interpreter) {
        return new SimpleProfiler(interpreter);
    }

    @Override
    public Runtime createRuntime(SimpleInterpreter interpreter) {
        return new SimpleRuntime(interpreter);
    }
   
}
