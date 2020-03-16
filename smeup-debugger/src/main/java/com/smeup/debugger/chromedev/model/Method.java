/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.model;

/**
 *
 * @author marco.lanari
 */
public class Method<P> {
    
    private String method;
    private P params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public P getParams() {
        return params;
    }

    public void setParams(P params) {
        this.params = params;
    }
   
}
