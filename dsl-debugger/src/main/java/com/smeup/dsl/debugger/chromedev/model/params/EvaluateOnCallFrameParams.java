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
public class EvaluateOnCallFrameParams implements Params{
    
    private String callFrameId;
    private String expression;
    private String objectGroup;
    private boolean includeCommandLineAPI;
    private boolean silent;
    private boolean returnByValue;
    private boolean generatePreview;

    public EvaluateOnCallFrameParams() {
    }

    public String getCallFrameId() {
        return callFrameId;
    }

    public void setCallFrameId(String callFrameId) {
        this.callFrameId = callFrameId;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getObjectGroup() {
        return objectGroup;
    }

    public void setObjectGroup(String objectGroup) {
        this.objectGroup = objectGroup;
    }

    public boolean isIncludeCommandLineAPI() {
        return includeCommandLineAPI;
    }

    public void setIncludeCommandLineAPI(boolean includeCommandLineAPI) {
        this.includeCommandLineAPI = includeCommandLineAPI;
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    public boolean isReturnByValue() {
        return returnByValue;
    }

    public void setReturnByValue(boolean returnByValue) {
        this.returnByValue = returnByValue;
    }

    public boolean isGeneratePreview() {
        return generatePreview;
    }

    public void setGeneratePreview(boolean generatePreview) {
        this.generatePreview = generatePreview;
    }
    
    
    
    
    
}
