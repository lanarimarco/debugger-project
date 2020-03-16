/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.endpoint;

import com.smeup.debugger.chromedev.messageshandlers.Debugger;
import com.smeup.debugger.chromedev.messageshandlers.Profiler;
import com.smeup.debugger.chromedev.messageshandlers.Runtime;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.smeup.debugger.chromedev.Debuggee;
/**
 *
 * @author marco.lanari
 */
class DebuggeeWrapper {
    
    private static final Logger LOGGER = Logger.getLogger(DebuggeeWrapper.class.getName());
    
    private final Runtime runtime;
    private final Debugger debugger;
    private final Profiler profiler;

    DebuggeeWrapper(Debuggee debuggee, String scriptId){
        LOGGER.log(Level.INFO, "Creating interpreter, scriptId: {0}", scriptId);
        Object interpreter = debuggee.createInterpreter(scriptId);
        LOGGER.log(Level.INFO, "Interpreter: {0} created succesfully", interpreter);
        runtime = debuggee.createRuntime(interpreter);
        debugger = debuggee.createDebugger(interpreter);
        profiler = debuggee.createProfiler(interpreter);
    }

    Runtime getRuntime() {
        return runtime;
    }

    Debugger getDebugger() {
        return debugger;
    }

    Profiler getProfiler() {
        return profiler;
    }
    
    
    
    
}
