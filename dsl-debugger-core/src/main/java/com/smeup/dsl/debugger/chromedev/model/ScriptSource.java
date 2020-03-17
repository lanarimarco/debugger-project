/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.chromedev.model;

/**
 *
 * @author marco.lanari
 */
public class ScriptSource {
    
    private String scriptSource;

    public ScriptSource() {
    }

    public ScriptSource(String scriptSource) {
        this.scriptSource = scriptSource;
    }
    
    

    public String getScriptSource() {
        return scriptSource;
    }

    public void setScriptSource(String scriptSource) {
        this.scriptSource = scriptSource;
    }

    @Override
    public String toString() {
        return "ScriptSource{" + "scriptSource=" + scriptSource + '}';
    }
    
    
}
