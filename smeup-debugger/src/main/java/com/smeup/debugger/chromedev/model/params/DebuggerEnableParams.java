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
public class DebuggerEnableParams implements Params{
    
    private int maxScriptsCacheSize;

    public int getMaxScriptsCacheSize() {
        return maxScriptsCacheSize;
    }

    public void setMaxScriptsCacheSize(int maxScriptsCacheSize) {
        this.maxScriptsCacheSize = maxScriptsCacheSize;
    }

    @Override
    public String toString() {
        return "DebuggerEnableParams{" + "maxScriptsCacheSize=" + maxScriptsCacheSize + '}';
    }
    
    
    
}
