/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debuggee.helloworld;

import com.smeup.debugger.chromedev.simpledebuggee.SimpleInterpreter;
import com.smeup.debugger.chromedev.simpledebuggee.model.SimpleDebuggeeContext;
import com.smeup.debugger.chromedev.simpledebuggee.model.SimpleScript;

/**
 *
 * @author marco.lanari
 */
public class HelloWorldInterpreter implements SimpleInterpreter{
    
    private final SimpleScript simpleScript = new SimpleScript("HelloWorld", "Hello world!!!");
    private final SimpleDebuggeeContext simpleDebuggeeContext = new SimpleDebuggeeContext();

    public HelloWorldInterpreter() {
        simpleDebuggeeContext.setScriptId("HelloWorld");
        simpleDebuggeeContext.setLineNumber(0);
        simpleDebuggeeContext.setFunctionName("main");
    }
    
    @Override
    public SimpleScript getSimpleScript(String scriptId) {
        return simpleScript;
    }

    @Override
    public SimpleDebuggeeContext getSimpleDebuggeeContext() {
        return simpleDebuggeeContext;
    }

    @Override
    public boolean stepOver() {
        System.out.println(simpleScript.getSource());
        return false;
    }
    
    @Override
    public boolean stepInto() {
        System.out.println(simpleScript.getSource());
        return false;
    }
    
    @Override
    public boolean stepOut() {
        System.out.println(simpleScript.getSource());
        return false;
    }

    @Override
    public boolean resume() {
        System.out.println(simpleScript.getSource());
        return false;
    }
    
}
