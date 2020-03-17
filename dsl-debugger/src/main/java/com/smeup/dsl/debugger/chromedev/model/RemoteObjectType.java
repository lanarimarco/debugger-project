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
public enum RemoteObjectType {
    object, function, undefined, string, number, bool, symbol, bigint,
    ;

    @Override
    public String toString() {
        if (this == bool) {
            return "boolean";
        }
        else {
            return super.toString();
        }
    }
    
    
}
