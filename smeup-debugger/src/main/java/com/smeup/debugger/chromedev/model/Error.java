/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.model;

/**
 *
 * @author marco.lanari
 */
public class Error {
    private int code;
    private String message;
    
    public Error() {
    }

    public Error(int code) {
        this.code = code;
    }

    public Error(String message) {
        this.message = message;
    }
   
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" + "code=" + code + ", message=" + message + '}';
    }

}
