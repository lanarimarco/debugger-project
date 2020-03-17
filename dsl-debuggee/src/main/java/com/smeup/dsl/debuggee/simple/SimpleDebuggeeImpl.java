/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debuggee.simple;


import com.smeup.dsl.debugger.chromedev.DebuggeeImpl;
import com.smeup.dsl.debugger.chromedev.simpledebuggee.SimpleDebuggee;
import com.smeup.dsl.debugger.chromedev.simpledebuggee.SimpleInterpreter;
import java.io.IOException;

/**
 *
 * @author marco.lanari
 */
@DebuggeeImpl (descr = "A simple debuggee implementation", id = "simple")
public class SimpleDebuggeeImpl extends SimpleDebuggee{
    

    @Override
    public SimpleInterpreter createInterpreter(String scriptId) {
        try {
            return new SimpleInterpreterImpl(scriptId);
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
}
