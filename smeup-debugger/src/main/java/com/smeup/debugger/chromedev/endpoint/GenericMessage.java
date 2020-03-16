/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.endpoint;

import com.google.gson.JsonElement;

/**
 *
 * @author marco.lanari
 */
public class GenericMessage {
    private final int id;
    private final String method;
    private final JsonElement params;

    public GenericMessage(int id, String method, JsonElement params) {
        this.id = id;
        this.method = method;
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public JsonElement getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "GenericMessage{" + "id=" + id + ", method=" + method + ", params=" + params + '}';
    }
    
}
