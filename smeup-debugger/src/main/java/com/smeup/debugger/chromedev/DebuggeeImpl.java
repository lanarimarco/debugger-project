/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author marco.lanari
 */
@Retention (value = RetentionPolicy.RUNTIME)
@Target (value = ElementType.TYPE)
public @interface DebuggeeImpl {
    String id();
    String descr();
}
