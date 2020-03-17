/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.chromedev.endpoint;

import com.google.gson.Gson;
import com.smeup.dsl.debugger.chromedev.model.Error;
import com.smeup.dsl.debugger.chromedev.model.Params;
import com.smeup.dsl.debugger.chromedev.model.Result;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author marco.lanari
 */
class MessageHandlerInvoker {
    
    private static final Logger LOGGER = Logger.getLogger(MessageHandlerInvoker.class.getName());
    
    enum MethodType {
        Runtime, Profiler, Debugger,
        
        ;
        
        private static MethodType getByMethod(String method) {
            Optional<MethodType> result =
                    Arrays.asList(values()).stream().filter(
                            value -> method.startsWith(value.name() + ".")).findFirst();
            if (result.isPresent()) {
                return result.get();
            }
            else {
                throw new IllegalArgumentException(method + " not handled");
            }
        } 
    }
    
    private Result invoke(GenericMessage genericMessage, Object messageHandlerInstance) 
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, 
            InvocationTargetException{
        String methodName = genericMessage.getMethod().substring(genericMessage.getMethod().indexOf(".") + 1);
        Optional<Method> result = Arrays.asList(messageHandlerInstance.getClass().getMethods()).
                stream().filter(method -> method.getName().equals(methodName)).findFirst();
        if (result.isPresent()) {
            Method m = result.get();
            if (genericMessage.getParams() == null) {
                return (Result)result.get().invoke(messageHandlerInstance, genericMessage.getId());
            }
            else {
                Optional<Class<?>> o = Arrays.asList(m.getParameterTypes()).stream().
                    filter(clazz -> Params.class.isAssignableFrom(clazz)).findFirst();
                if (o.isPresent()) {
                    return (Result)result.get().invoke(messageHandlerInstance, 
                            genericMessage.getId(), 
                            new Gson().fromJson(genericMessage.getParams().toString(), o.get()));
                }
                else {
                    return (Result)result.get().invoke(messageHandlerInstance, 
                            genericMessage.getId());
                }
            }
        }
        else {
            throw new NoSuchMethodException(MessageFormat.format(
                    "Method {0} not found in {1}", methodName, messageHandlerInstance.getClass().getName()));
        }
    }
    
   
    
    Result invoke(DebuggeeWrapper debuggeeWrapper, GenericMessage message) {
        try {
            MethodType methodType;
            switch (methodType = MethodType.getByMethod(message.getMethod())) {
                case Debugger: 
                    return invoke(message, debuggeeWrapper.getDebugger());
                case Profiler: 
                    return invoke(message, debuggeeWrapper.getProfiler());
                case Runtime: 
                    return invoke(message, debuggeeWrapper.getRuntime());
                default: throw new IllegalArgumentException("Method type: " + methodType + " not handled");
            }
        }
        catch(Exception e) {
            String errorMessage = e.getMessage();
            if (e instanceof InvocationTargetException) {
                errorMessage = ((InvocationTargetException) e).getCause().getMessage();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            Result result = new Result(message.getId());
            result.setError(new Error(-1, errorMessage));
            return result;
        }
        
    }
    
    
}
