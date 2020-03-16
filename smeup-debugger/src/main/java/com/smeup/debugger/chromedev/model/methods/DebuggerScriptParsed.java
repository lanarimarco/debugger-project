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
public class DebuggerScriptParsed extends DebuggeeMethod<DebuggerScriptParsed.Params> {

    public static class Params {

        private final String scriptId;
        private final String url;
        private final int startLine;
        private final int startColumn;
        private final int endLine;
        private final int endColumn;
        private final int executionContextId;
        private final String hash;

        public Params(String scriptId, String url, int startLine, int startColumn, int endLine, int endColumn, int executionContextId, String hash) {
            this.scriptId = scriptId;
            this.url = url;
            this.startLine = startLine;
            this.startColumn = startColumn;
            this.endLine = endLine;
            this.endColumn = endColumn;
            this.executionContextId = executionContextId;
            this.hash = hash;
        }

        public String getScriptId() {
            return scriptId;
        }

        public String getUrl() {
            return url;
        }

        public int getStartLine() {
            return startLine;
        }

        public int getStartColumn() {
            return startColumn;
        }

        public int getEndLine() {
            return endLine;
        }

        public int getEndColumn() {
            return endColumn;
        }

        public int getExecutionContextId() {
            return executionContextId;
        }

        public String getHash() {
            return hash;
        }
        
        
        

    }

    public DebuggerScriptParsed() {
        setMethod("Debugger.scriptParsed");
    }

}
