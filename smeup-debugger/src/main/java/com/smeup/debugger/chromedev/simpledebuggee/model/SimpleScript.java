/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.simpledebuggee.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * A script
 *
 * @author marco.lanari
 */
public class SimpleScript {

    private final String scriptId;
    private final String source;
    private String name;

    /**
     * Create e script
     *
     * @param scriptId Unique script id
     * @param source Source code of script
     */
    public SimpleScript(String scriptId, String source) {
        this.scriptId = scriptId;
        this.source = source;
    }

    public String getScriptId() {
        return scriptId;
    }

    public String getSource() {
        return source;
    }

    public String getName() {
        if (name == null) {
            return getScriptId();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SimpleScript createBy(String scriptId, URL url) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                (url.openStream())))) {
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return new SimpleScript(scriptId, sb.toString());
    }
    
}
