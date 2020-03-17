/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.chromedev;

import com.smeup.dsl.debugger.chromedev.model.methods.DebuggerPaused;
import com.smeup.dsl.debugger.chromedev.model.methods.DebuggerResumed;
import com.smeup.dsl.debugger.chromedev.model.methods.DebuggerScriptParsed;
import com.smeup.dsl.debugger.chromedev.model.methods.RuntimeExecutionContextCreated;



/**
 * Consente a debuggee di inbiare eventi al debugger
 * @author marco.lanari
 */
public interface DebuggeeEventDispatcher {
    
    public void notifyRuntimeExecutionContextCreated(RuntimeExecutionContextCreated runtimeExecutionContextCreated);
    public void notifyDebuggerScriptParsed(DebuggerScriptParsed debuggerScriptParsed);
    public void notifyDebuggerPaused(DebuggerPaused debuggerPaused);
    public void notifyDebuggerResumed(DebuggerResumed debuggerResumed);
    public void notifyCloseSession(String reasons);
    
}
