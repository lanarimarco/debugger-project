/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.model.methods;

import com.google.gson.annotations.SerializedName;
import com.smeup.debugger.chromedev.model.DebuggeeMethod;
import java.util.List;

/**
 *
 * @author marco.lanari
 */
public class DebuggerPaused extends DebuggeeMethod<DebuggerPaused.Params> {

    public enum Reason {
        ambiguous, _assert, debugCommand, DOM, EventListener, exception,
        instrumentation, OOM, other, promiseRejection, XHR,;

        @Override
        public String toString() {
            if (this.equals(_assert)) {
                return "assert";
            } else {
                return super.toString();
            }
        }
    }

    public enum Type {
        object, function, undefined, string, number, _boolean, symbol, bigint;

        @Override
        public String toString() {
            if (this.equals(_boolean)) {
                return "boolean";
            } else {
                return super.toString();
            }
        }
    }

    public enum Subtype {
        _array, _null, node, regexp, date, map, set, weakmap, weakset, iterator,
        generator, error, proxy, promise, typedarray, arraybuffer, dataview;

        public String toString() {
            switch (this) {
                case _array:
                    return "array";
                case _null:
                    return "null";
                default:
                    return super.toString();
            }
        }
    }

    public enum ScopeType {
        global, local, with, closure, _catch, block, script, eval, module;

        @Override
        public String toString() {
            if (this.equals(_catch)) {
                return "catch";
            } else {
                return super.toString();
            }
        }

    }

    public static class CallFrame {

        private final String callFrameId;
        private final String functionName;
        private Location functionLocation;
        private final Location location;
        private final String url;
        private final List<Scope> scopeChain;
        @SerializedName (value = "this")
        private final RemoteObject thisObject;  
        private RemoteObject returnValue;

        public CallFrame(String callFrameId, String functionName, 
                Location location, String url, List<Scope> scopeChain, RemoteObject thisObject) {
            this.callFrameId = callFrameId;
            this.functionName = functionName;
            this.location = location;
            this.url = url;
            this.scopeChain = scopeChain;
            this.thisObject = thisObject;
        }

        public Location getFunctionLocation() {
            return functionLocation;
        }

        public void setFunctionLocation(Location functionLocation) {
            this.functionLocation = functionLocation;
        }

        public RemoteObject getReturnValue() {
            return returnValue;
        }

        public void setReturnValue(RemoteObject returnValue) {
            this.returnValue = returnValue;
        }

        public String getCallFrameId() {
            return callFrameId;
        }

        public String getFunctionName() {
            return functionName;
        }

        public Location getLocation() {
            return location;
        }

        public String getUrl() {
            return url;
        }

        public List<Scope> getScopeChain() {
            return scopeChain;
        }

        public RemoteObject getThisObject() {
            return thisObject;
        }
        
        
        
        
    }

    public static class Location {

        private final String scriptId;
        private final int lineNumber;
        private int columnNumber;

        public Location(String scriptId, int lineNumber) {
            this.scriptId = scriptId;
            this.lineNumber = lineNumber;
        }

        public int getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(int columnNumber) {
            this.columnNumber = columnNumber;
        }

        public String getScriptId() {
            return scriptId;
        }

        public int getLineNumber() {
            return lineNumber;
        }

    }

    public static class RemoteObject {

        private final Type type;
        private Subtype subtype;
        private String className;
        private String description;
        private String objectId;

        public RemoteObject(Type type) {
            this.type = type;
        }

        public Subtype getSubtype() {
            return subtype;
        }

        public void setSubtype(Subtype subtype) {
            this.subtype = subtype;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public Type getType() {
            return type;
        }
        
        

    }

    public static class Scope {

        private final ScopeType type;
        private final RemoteObject object;
        private String name;
        private Location startLocation;
        private Location endLocation;

        public Scope(ScopeType type, RemoteObject object) {
            this.type = type;
            this.object = object;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Location getStartLocation() {
            return startLocation;
        }

        public void setStartLocation(Location startLocation) {
            this.startLocation = startLocation;
        }

        public Location getEndLocation() {
            return endLocation;
        }

        public void setEndLocation(Location endLocation) {
            this.endLocation = endLocation;
        }

        public ScopeType getType() {
            return type;
        }

        public RemoteObject getObject() {
            return object;
        }
       
    }

    public static class Params {

        private final Reason reason;
        private final List<CallFrame> callFrames;
        private List<String> hitBreakpoints;

        public Params(Reason reason, List<CallFrame> callFrames) {
            this.reason = reason;
            this.callFrames = callFrames;
        }

        public Reason getReason() {
            return reason;
        }

        public List<String> getHitBreakpoints() {
            return hitBreakpoints;
        }

        public void setHitBreakpoints(List<String> hitBreakpoints) {
            this.hitBreakpoints = hitBreakpoints;
        }

        public List<CallFrame> getCallFrames() {
            return callFrames;
        }
    }

    public DebuggerPaused() {
        setMethod("Debugger.paused");
    }

}
