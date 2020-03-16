/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.simpledebuggee;
import com.smeup.debugger.chromedev.messageshandlers.Runtime;
import com.smeup.debugger.chromedev.model.Error;
import com.smeup.debugger.chromedev.model.Properties;
import com.smeup.debugger.chromedev.model.Result;
import com.smeup.debugger.chromedev.model.methods.DebuggeeMethodFactory;
import com.smeup.debugger.chromedev.model.params.GetPropertiesParams;
import com.smeup.debugger.chromedev.simpledebuggee.model.SimpleDebuggeeContext;
import com.smeup.debugger.chromedev.simpledebuggee.model.SimpleScript;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author marco.lanari
 */
public class SimpleRuntime implements Runtime{
    
    private final SimpleInterpreter interpreter;

    public SimpleRuntime(SimpleInterpreter simpleInterpreter) {
        this.interpreter = simpleInterpreter;
    }
    
    @Override
    public Result enable(int id) {
        return new Result(id);
    }
    
    @Override
    public Result getIsolateId(int id) {
        Result result = new Result(id);
        Error error = new Error(-32601, "'Runtime.getIsolateId' wasn't found");
        result.setError(error);
        return result;
    }

    @Override
    public Result runIfWaitingForDebugger(int id) {
        Result result = new Result(id);
        result.afterSend(debuggeeEventDispatcher -> {
            int contextId = 1;
            try {
                SimpleDebuggeeContext simpleDebuggeeContext = interpreter.getSimpleDebuggeeContext();
                SimpleScript simpleScript = interpreter.getSimpleScript(simpleDebuggeeContext.getScriptId());
                
                debuggeeEventDispatcher.notifyRuntimeExecutionContextCreated(DebuggeeMethodFactory.
                        createRuntimeExecutionContextCreated(contextId, "", "Main context"));
                
                debuggeeEventDispatcher.notifyDebuggerScriptParsed(DebuggeeMethodFactory.createDebuggerScriptParsed(
                                simpleScript.getScriptId(), simpleScript.getName(), 
                                simpleScript.getSource(), contextId));
                
                debuggeeEventDispatcher.notifyDebuggerPaused(DebuggeeMethodFactory.
                        createDebuggerPaused(simpleScript.getScriptId(), 
                                simpleScript.getName(), simpleDebuggeeContext.getFunctionName(), 
                                simpleDebuggeeContext.getLineNumber()));
            } catch (NoSuchAlgorithmException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return result;
    }
    
    @Override
    public Result<Properties> getProperties(int id, GetPropertiesParams params) {
        return new Result(id);
    }
    
    
    
}
