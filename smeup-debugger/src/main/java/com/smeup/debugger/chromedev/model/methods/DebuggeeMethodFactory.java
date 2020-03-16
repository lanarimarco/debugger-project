/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.model.methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 *
 * @author marco.lanari
 */
public class DebuggeeMethodFactory {
    
    
    public static DebuggerPaused createDebuggerPaused(String scriptId, String nameOrUrl,
            String functionName, int lineNumber) {
        DebuggerPaused debuggerPaused = new DebuggerPaused();
        DebuggerPaused.RemoteObject remoteObject = new DebuggerPaused.RemoteObject(DebuggerPaused.Type.object);
        DebuggerPaused.Scope scope = new DebuggerPaused.Scope(DebuggerPaused.ScopeType.local, remoteObject);
        DebuggerPaused.Location location = new DebuggerPaused.Location(scriptId, lineNumber);
        DebuggerPaused.CallFrame callFrame = new DebuggerPaused.CallFrame(
                "0", functionName, location, nameOrUrl, Arrays.asList(scope), remoteObject);
        //callFrame.setFunctionLocation(location);
        DebuggerPaused.Params params = new DebuggerPaused.Params(DebuggerPaused.Reason.other, 
            Arrays.asList(callFrame));
        debuggerPaused.setParams(params);
        return debuggerPaused;
    }
    
    public static RuntimeExecutionContextCreated createRuntimeExecutionContextCreated(int contextId, 
            String origin, String name) {
        RuntimeExecutionContextCreated.ExecutionContextDescription context = 
                new RuntimeExecutionContextCreated.ExecutionContextDescription(contextId, origin, name);
        RuntimeExecutionContextCreated.Params params = new RuntimeExecutionContextCreated.Params(context);
        RuntimeExecutionContextCreated runtimeExecutionContextCreated = 
                new RuntimeExecutionContextCreated();
        runtimeExecutionContextCreated.setParams(params);
        return runtimeExecutionContextCreated;
    }
    
    public static DebuggerScriptParsed createDebuggerScriptParsed(String scriptId, String nameOrUrl, String source, int contextId) 
            throws IOException, NoSuchAlgorithmException {
     
        StringBuilder sb = new StringBuilder();
        String line;
        int currentLine = 0;
        int endCol = 0;
        try (BufferedReader in = new BufferedReader(new StringReader(source))) {
            while ((line = in.readLine()) != null) {
                currentLine++;
                if (line.length() > endCol) {
                    endCol = line.length();
                } 
                sb.append(line).append("\n");
            }
        }

        DebuggerScriptParsed.Params script = new DebuggerScriptParsed.Params(scriptId, nameOrUrl, 0, 0, currentLine, endCol, contextId, getHash(sb.toString()));
        DebuggerScriptParsed debuggerScriptParsed = new DebuggerScriptParsed();
        debuggerScriptParsed.setParams(script);
        return debuggerScriptParsed;
    }
    
    

    private static String getHash(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
