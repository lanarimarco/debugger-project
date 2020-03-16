/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.simpledebuggee;

import com.smeup.debugger.chromedev.simpledebuggee.model.SimpleDebuggeeContext;
import com.smeup.debugger.chromedev.simpledebuggee.model.SimpleScript;

/**
 * Debuggee interpreter
 * @author marco.lanari
 */
public interface SimpleInterpreter {
    
    /**
     * @param scriptId Id of script
     * @return Instance of script intepreted
     */
    public SimpleScript getSimpleScript(String scriptId);
    
    /**
     * @return Instance of debuggee context
     */
    public SimpleDebuggeeContext getSimpleDebuggeeContext();
    
    
    /**
     * Debugger send a step over
     * @return <code>false</code> when last statement is interpreted
     */
    public boolean stepOver();
    
    /**
     * Debugger send a step into
     * @return <code>false</code> when last statement is interpreted
     */
    public boolean stepInto();
    
    /**
     * Debugger send a step out
     * @return <code>false</code> when last statement is interpreted
     */
    public boolean stepOut();
    
     /**
     * Debugger send a resume. Debuggee continue until next breakpoint or  "game over"
     * @return <code>false</code> when last statement is interpreted
     */
    public boolean resume();
    
}
