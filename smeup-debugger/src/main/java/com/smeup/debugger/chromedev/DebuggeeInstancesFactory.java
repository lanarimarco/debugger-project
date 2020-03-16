/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.debugger.chromedev;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

/**
 *
 * @author marco.lanari
 */
public class DebuggeeInstancesFactory {
    
    private final Set<Class<?>> debuggeeImplList;
    private final List<String> packagesList;

    public DebuggeeInstancesFactory(List<String> packagesList) {
        this.packagesList = packagesList;
        String[] packages = new String[packagesList.size()];
        packages = packagesList.toArray(packages);
        ConfigurationBuilder configurationBuilder = 
        new ConfigurationBuilder().forPackages(packages);
        Reflections reflections = new Reflections(configurationBuilder);
        debuggeeImplList = reflections.getTypesAnnotatedWith(DebuggeeImpl.class);
    }


    /**
     * Get an instance of class Debuggee annotated by {@link DebuggeeImpl} and
     * given debuggeeId
     * @param debuggeeId Id
     * @return Insstance
     */
    public Debuggee createInstanceBy(String debuggeeId) throws IllegalArgumentException,
            InstantiationException, IllegalAccessException {

        List<Class> list = debuggeeImplList.stream().filter(c -> c.getAnnotation(DebuggeeImpl.class).id().equals(debuggeeId)).collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new IllegalArgumentException(MessageFormat.format("No type annotated with: {0}, "
                    + "identified by debuggeeId: {1} found in packages: {2}", DebuggeeImpl.class.getName(), 
                        debuggeeId, packagesList));
        } else if (list.size() > 1) {
            throw new IllegalArgumentException(MessageFormat.format("Debuggee identified by: {0} found in: {1} classes", debuggeeId, list));
        } else {
            return (Debuggee) list.get(0).newInstance();
        }
    }
    
}
