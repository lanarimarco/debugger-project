/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.simpledebuggee;

import com.smeup.debugger.chromedev.messageshandlers.Profiler;
import com.smeup.debugger.chromedev.model.Result;

/**
 *
 * @author marco.lanari
 */
public class SimpleProfiler implements Profiler{
    
    private final SimpleInterpreter simpleInterpreter;

    public SimpleProfiler(SimpleInterpreter simpleInterpreter) {
        this.simpleInterpreter = simpleInterpreter;
    }
    
    @Override
    public Result enable(int id) {
        return new Result(id);
    }
    
    
    
}
