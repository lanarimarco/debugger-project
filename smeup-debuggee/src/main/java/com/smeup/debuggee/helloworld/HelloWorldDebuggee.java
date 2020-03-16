/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debuggee.helloworld;

import com.smeup.debugger.chromedev.DebuggeeImpl;
import com.smeup.debugger.chromedev.simpledebuggee.SimpleDebuggee;
import com.smeup.debugger.chromedev.simpledebuggee.SimpleInterpreter;

/**
 *
 * @author marco.lanari
 */
@DebuggeeImpl (id = "helloworld", descr = "My debuggee implementation")
public class HelloWorldDebuggee extends SimpleDebuggee{

    public HelloWorldDebuggee() {
    }

    @Override
    public SimpleInterpreter createInterpreter(String scriptId) {
        return new HelloWorldInterpreter();
    }
    
    
    
}
