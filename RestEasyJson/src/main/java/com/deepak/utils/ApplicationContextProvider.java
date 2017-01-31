package com.deepak.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	 /** The application context */
    private static ApplicationContext ctx;
     
     
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
            ctx=context;
    }
    public static ApplicationContext getApplicationContext() {  
            return ctx;  
        } 
}
