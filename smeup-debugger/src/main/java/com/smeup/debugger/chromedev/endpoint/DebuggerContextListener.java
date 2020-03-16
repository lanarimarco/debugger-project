/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev.endpoint;

import com.smeup.debugger.chromedev.Consts;
import com.smeup.debugger.chromedev.DebuggeeInstancesFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DebuggerContextListener implements ServletContextListener{
    
    private static final Logger LOGGER = Logger.getLogger(DebuggerContextListener.class.getName());
    
    //non mi piace ma non riesco a passare il servletcontext all'endpoint
    public static DebuggeeInstancesFactory DEBUGGEE_INSTANCE_FACTORY;
    

    public DebuggerContextListener() {
    }
    
    

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.log(Level.INFO, "Inizializing DebuggeeInstancesFactory");
        
        String packages = sce.getServletContext().getInitParameter(Consts.DEBUGGEE_IMPL_PACKAGES);
        List<String> packagesList = new ArrayList<String>();
        packagesList.addAll(Arrays.asList(Consts.DEBUGGEE_IMP_DEFAULT_PACKAGE.split(",")));
        if (packages != null && packages.trim().replaceAll("\\s+", "").length() > 0) {
            packagesList.addAll(Arrays.asList(packages.trim().replaceAll("\\s+", "")));
        }
        DEBUGGEE_INSTANCE_FACTORY = new DebuggeeInstancesFactory(packagesList);
        LOGGER.log(Level.INFO, "DebuggeeInstancesFactory inizialized");
       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
    
    
}
