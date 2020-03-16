/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.model.methods;

import com.smeup.debugger.chromedev.model.DebuggeeMethod;

/**
 *
 * @author marco.lanari
 */
public class RuntimeExecutionContextCreated extends DebuggeeMethod<RuntimeExecutionContextCreated.Params>{
    
    public static class ExecutionContextDescription {
        private int id;
        private String origin;
        private String name;

        public ExecutionContextDescription(int id, String origin, String name) {
            this.id = id;
            this.origin = origin;
            this.name = name;
        }
        
        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Context{" + "origin=" + origin + ", name=" + name + ", id=" + id + '}';
        }
        
    }
    
    public static class Params {
        private ExecutionContextDescription context;

        public Params(ExecutionContextDescription context) {
            this.context = context;
        }
       
        public ExecutionContextDescription getContext() {
            return context;
        }

        public void setContext(ExecutionContextDescription context) {
            this.context = context;
        }
    }
   
    public RuntimeExecutionContextCreated() {
        setMethod("Runtime.executionContextCreated");
    }

  
}
