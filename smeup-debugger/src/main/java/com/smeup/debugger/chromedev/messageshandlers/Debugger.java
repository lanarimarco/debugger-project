/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.messageshandlers;

import com.smeup.debugger.chromedev.model.Result;
import com.smeup.debugger.chromedev.model.ScriptSource;
import com.smeup.debugger.chromedev.model.params.DebuggerEnableParams;
import com.smeup.debugger.chromedev.model.params.EvaluateOnCallFrameParams;
import com.smeup.debugger.chromedev.model.params.GetScriptSourceParams;
import com.smeup.debugger.chromedev.model.params.SetAsyncCallStackDepthParams;
import com.smeup.debugger.chromedev.model.params.SetBlackboxPatternsParams;
import com.smeup.debugger.chromedev.model.params.SetPauseOnExceptionParams;

/**
 *
 * @author marco.lanari
 */
public interface Debugger {
    
    public Result enable(int id, DebuggerEnableParams params);
    
    public Result setPauseOnExceptions(int id, SetPauseOnExceptionParams params);
    
    public Result setAsyncCallStackDepth(int id, SetAsyncCallStackDepthParams setAsyncCallStackDepthParams);
    
    public Result setBlackboxPatterns(int id, SetBlackboxPatternsParams params);
    
    public Result<ScriptSource> getScriptSource(int id, GetScriptSourceParams params);
    
    public Result evaluateOnCallFrame(int id, EvaluateOnCallFrameParams params);
    
    public Result stepOver(int id);
    
    public Result stepInto(int id);
    
    public Result stepOut(int id);
    
    public Result resume(int id);
    
    
    
}
