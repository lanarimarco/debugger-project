/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.model.params;

import com.smeup.debugger.chromedev.model.Params;

/**
 *
 * @author marco.lanari
 */
public class GetScriptSourceParams implements Params {
    
    private String scriptId;

    public String getScriptId() {
        return scriptId;
    }

    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    @Override
    public String toString() {
        return "GetScriptSourceParams{" + "scriptId=" + scriptId + '}';
    }
    
    
}
