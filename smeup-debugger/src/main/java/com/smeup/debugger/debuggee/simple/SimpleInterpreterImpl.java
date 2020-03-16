/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.debuggee.simple;

import com.smeup.debugger.chromedev.simpledebuggee.SimpleInterpreter;
import com.smeup.debugger.chromedev.simpledebuggee.model.SimpleDebuggeeContext;
import com.smeup.debugger.chromedev.simpledebuggee.model.SimpleScript;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Keep in project to test e simpledebuggee framework
/**
 * Simple interpreter, interprets only a source file.
 * @author marco.lanari
 */
public class SimpleInterpreterImpl implements SimpleInterpreter{
  
    private static final Logger LOGGER = Logger.getLogger(SimpleInterpreterImpl.class.getName());

    private final SimpleScript simpleScript;
    private final SimpleDebuggeeContext simpleDebuggeeContext;
    private final List<String> statements; 
    
    

    public SimpleInterpreterImpl(String scriptId) throws IOException {
        simpleScript = SimpleScript.createBy(scriptId, getClass().getResource("/simple/" + scriptId));
        simpleDebuggeeContext = new SimpleDebuggeeContext();
        simpleDebuggeeContext.setFunctionName("main");
        simpleDebuggeeContext.setLineNumber(0);
        simpleDebuggeeContext.setScriptId(scriptId);
        statements = Arrays.asList(simpleScript.getSource().split("\n"));
    }
    
    

    @Override
    public SimpleScript getSimpleScript(String scriptId) {
        //interprets only a source file
        return simpleScript;
    }

    @Override
    public SimpleDebuggeeContext getSimpleDebuggeeContext() {
        return simpleDebuggeeContext;
    }

    @Override
    public boolean stepOver() {
        LOGGER.log(Level.INFO, "Interpreting {0}", 
                statements.get(simpleDebuggeeContext.getLineNumber()));
        simpleDebuggeeContext.setLineNumber(simpleDebuggeeContext.getLineNumber()+1);
        return simpleDebuggeeContext.getLineNumber() < statements.size();   
    }

    @Override
    public boolean stepInto() {
        LOGGER.log(Level.INFO, "Interpreting {0}", 
                statements.get(simpleDebuggeeContext.getLineNumber()));
        simpleDebuggeeContext.setLineNumber(simpleDebuggeeContext.getLineNumber()+1);
        return simpleDebuggeeContext.getLineNumber() < statements.size();
    }

    @Override
    public boolean stepOut() {
        LOGGER.log(Level.INFO, "Interpreting {0}", 
                statements.get(simpleDebuggeeContext.getLineNumber()));
        simpleDebuggeeContext.setLineNumber(simpleDebuggeeContext.getLineNumber()+1);
        return simpleDebuggeeContext.getLineNumber() < statements.size();
    }
    
    @Override
    public boolean resume() {
        do{}while(stepOver());
        return false;
    }
    
}
