/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.chromedev.simpledebuggee;

import com.smeup.dsl.debugger.chromedev.messageshandlers.Debugger;
import com.smeup.dsl.debugger.chromedev.model.Result;
import com.smeup.dsl.debugger.chromedev.model.ScriptSource;
import com.smeup.dsl.debugger.chromedev.model.methods.DebuggeeMethodFactory;
import com.smeup.dsl.debugger.chromedev.model.methods.DebuggerResumed;
import com.smeup.dsl.debugger.chromedev.model.params.DebuggerEnableParams;
import com.smeup.dsl.debugger.chromedev.model.params.EvaluateOnCallFrameParams;
import com.smeup.dsl.debugger.chromedev.model.params.GetScriptSourceParams;
import com.smeup.dsl.debugger.chromedev.model.params.SetAsyncCallStackDepthParams;
import com.smeup.dsl.debugger.chromedev.model.params.SetBlackboxPatternsParams;
import com.smeup.dsl.debugger.chromedev.model.params.SetPauseOnExceptionParams;
import com.smeup.dsl.debugger.chromedev.simpledebuggee.model.SimpleDebuggeeContext;
import com.smeup.dsl.debugger.chromedev.simpledebuggee.model.SimpleScript;

/**
 *
 * @author marco.lanari
 */
public class SimpleDebugger implements Debugger{
    
    private final SimpleInterpreter interpreter;

    public SimpleDebugger(SimpleInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public Result enable(int id, DebuggerEnableParams params) {
        return new Result(id);
    }

    @Override
    public Result setPauseOnExceptions(int id, SetPauseOnExceptionParams params) {
        return new Result(id);
    }

    @Override
    public Result setAsyncCallStackDepth(int id, SetAsyncCallStackDepthParams setAsyncCallStackDepthParams) {
        return new Result(id);
    }

    @Override
    public Result setBlackboxPatterns(int id, SetBlackboxPatternsParams params) {
        return new Result(id);
    }

    @Override
    public Result<ScriptSource> getScriptSource(int id, GetScriptSourceParams params) {
         return new Result<>(id, new ScriptSource(interpreter.getSimpleScript(params.getScriptId()).getSource()));
    }

    @Override
    public Result evaluateOnCallFrame(int id, EvaluateOnCallFrameParams params) {
        return new Result(id);
    }
    
    @Override
    public Result stepOver(int id) {
        return createResultAfterStep(id, interpreter.stepOver());
    }

    @Override
    public Result stepInto(int id) {
        return createResultAfterStep(id, interpreter.stepInto());
    }

    @Override
    public Result stepOut(int id) {
        return createResultAfterStep(id, interpreter.stepOut());
    }

    @Override
    public Result resume(int id) {
        return createResultAfterStep(id, interpreter.resume());
    }
    
    private Result createResultAfterStep(int id, boolean hasNext) {
        if (hasNext) {
            Result result = new Result(id);
            result.afterSend(d -> {
                d.notifyDebuggerResumed(new DebuggerResumed());
                SimpleDebuggeeContext simpleDebuggeeContext = interpreter.getSimpleDebuggeeContext();
                SimpleScript simpleScript = interpreter.getSimpleScript(simpleDebuggeeContext.getScriptId());
                d.notifyDebuggerPaused(DebuggeeMethodFactory.createDebuggerPaused(simpleDebuggeeContext.getScriptId(), simpleScript.getName(),
                        simpleDebuggeeContext.getFunctionName(), simpleDebuggeeContext.getLineNumber()));
            });
            return result;
        }
        else {
            Result result = new Result(id);
            result.afterSend(d -> {
                d.notifyCloseSession("Interpreted session closed");
            });
            return result;
        }
    } 
    
}
