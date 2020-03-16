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
public class SetPauseOnExceptionParams implements Params {
    
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SetPauseOnExceptionParams{" + "state=" + state + '}';
    }
    
    
}
