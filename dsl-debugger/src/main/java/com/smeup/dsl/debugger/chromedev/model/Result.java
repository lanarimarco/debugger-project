/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.dsl.debugger.chromedev.model;

import com.smeup.dsl.debugger.chromedev.messageshandlers.DebuggeeMessageSender;

/**
 * Rappresenta la risposta ad una richiesta fatta dal debugger
 *
 * @author marco.lanari
 */
public class Result<T> {

    private int id;
    private T result;
    private transient DebuggeeMessageSender debuggeeMessageSender;
    private Error error;

    public Result() {
    }

    public Result(int id) {
        this.id = id;
    }

    public Result(int id, T result) {
        this.id = id;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
    
    public void afterSend(DebuggeeMessageSender debuggeeMessageSender) {
        this.debuggeeMessageSender = debuggeeMessageSender;
    }

    public DebuggeeMessageSender getDebuggeeMessageSender() {
        return debuggeeMessageSender;
    }

    @Override
    public String toString() {
        return "Result{" + "id=" + id + ", result=" + result + '}';
    }

}
