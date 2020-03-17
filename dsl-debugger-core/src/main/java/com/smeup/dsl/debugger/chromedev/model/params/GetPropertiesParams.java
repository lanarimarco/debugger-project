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
public class GetPropertiesParams implements Params {
    
    private String objectId;
    private boolean ownProperties;
    private boolean accessorPropertiesOnly;
    private boolean generatePreview;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean isOwnProperties() {
        return ownProperties;
    }

    public void setOwnProperties(boolean ownProperties) {
        this.ownProperties = ownProperties;
    }

    public boolean isAccessorPropertiesOnly() {
        return accessorPropertiesOnly;
    }

    public void setAccessorPropertiesOnly(boolean accessorPropertiesOnly) {
        this.accessorPropertiesOnly = accessorPropertiesOnly;
    }

    public boolean isGeneratePreview() {
        return generatePreview;
    }

    public void setGeneratePreview(boolean generatePreview) {
        this.generatePreview = generatePreview;
    }

    @Override
    public String toString() {
        return "GetPropertiesParams{" + "objectId=" + objectId + ", ownProperties=" + ownProperties + ", accessorPropertiesOnly=" + accessorPropertiesOnly + ", generatePreview=" + generatePreview + '}';
    }
    
    
    
}
