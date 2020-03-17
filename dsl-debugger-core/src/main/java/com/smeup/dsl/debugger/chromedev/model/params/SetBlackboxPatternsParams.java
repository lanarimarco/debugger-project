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
public class SetBlackboxPatternsParams implements Params {
    
    private String[] patterns;

    public String[] getPatterns() {
        return patterns;
    }

    public void setPatterns(String[] patterns) {
        this.patterns = patterns;
    }

    @Override
    public String toString() {
        return "SetBlackboxPatternsParams{" + "patterns=" + patterns + '}';
    }
    
    
}
