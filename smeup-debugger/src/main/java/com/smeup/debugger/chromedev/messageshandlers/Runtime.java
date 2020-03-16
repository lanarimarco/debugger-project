/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.messageshandlers;

import com.smeup.debugger.chromedev.model.Properties;
import com.smeup.debugger.chromedev.model.Result;
import com.smeup.debugger.chromedev.model.params.GetPropertiesParams;

/**
 *
 * @author marco.lanari
 */
public interface Runtime {    
    
    public Result enable(int id);
    
    public Result runIfWaitingForDebugger(int id);
    
    public Result<Properties> getProperties(int id, GetPropertiesParams params);
    
    public Result getIsolateId(int id);
   
}
