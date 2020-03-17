/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.chromedev.model.params;

import com.smeup.dsl.debugger.chromedev.model.Params;

/**
 *
 * @author marco.lanari
 */
public class SetAsyncCallStackDepthParams implements Params {
    
    private int maxDepth;

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public String toString() {
        return "SetAsyncCallStackDepthParams{" + "maxDepth=" + maxDepth + '}';
    }
    
    
}
