/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.model;

import java.util.ArrayList;
import java.util.List;

/**
 * I costruttori non vuoti in quanto proprietà obbligatoria
 * @author marco.lanari
 */
public class Properties {
   
    
    public static class PropertyDescriptor {
        
        private boolean isOwn;
        private boolean enumerable;
        private String name;
        private RemoteObject value;
        private boolean configurable;
        private boolean writable;

        public PropertyDescriptor() {
        }

        public PropertyDescriptor(String name) {
            this.name = name;
        }
        
        public boolean isIsOwn() {
            return isOwn;
        }

        public void setIsOwn(boolean isOwn) {
            this.isOwn = isOwn;
        }

        public boolean isEnumerable() {
            return enumerable;
        }

        public void setEnumerable(boolean enumerable) {
            this.enumerable = enumerable;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public RemoteObject getValue() {
            return value;
        }

        public void setValue(RemoteObject value) {
            this.value = value;
        }

        public boolean isConfigurable() {
            return configurable;
        }

        public void setConfigurable(boolean configurable) {
            this.configurable = configurable;
        }

        public boolean isWritable() {
            return writable;
        }

        public void setWritable(boolean writable) {
            this.writable = writable;
        }

        @Override
        public String toString() {
            return "Variable{" + "isOwn=" + isOwn + ", enumerable=" + enumerable + ", name=" + name + ", value=" + value + ", configurable=" + configurable + ", writable=" + writable + '}';
        }
    }
    
    
    public static class InternalPropertyDescriptor {
        private String name;
        private RemoteObject value;

        public InternalPropertyDescriptor(String name) {
            this.name = name;
        }

        public InternalPropertyDescriptor() {
        }
        
        

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public RemoteObject getValue() {
            return value;
        }

        public void setValue(RemoteObject value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "InternalPropertyDescriptor{" + "name=" + name + ", value=" + value + '}';
        }
        
        
    }
    
    public static class RemoteObject {
        private String description;
        private RemoteObjectType type;

        public RemoteObject() {
        }

        public RemoteObject(RemoteObjectType type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public RemoteObjectType getType() {
            return type;
        }

        public void setType(RemoteObjectType type) {
            this.type = type;
        }
    }
  
    private List<PropertyDescriptor> result;
    private List<InternalPropertyDescriptor> internalProperties;

    public Properties(List<PropertyDescriptor> result) {
        this.result = result;
    }

    public Properties() {
        this(new ArrayList<>());
    }
   
 
    public List<PropertyDescriptor> getResult() {
        return result;
    }

   
    public void setResult(List<PropertyDescriptor> result) {
        this.result = result;
    }

    public List<InternalPropertyDescriptor> getInternalProperties() {
        return internalProperties;
    }

    public void setInternalProperties(List<InternalPropertyDescriptor> internalProperties) {
        this.internalProperties = internalProperties;
    }
    
    

    
    
}
