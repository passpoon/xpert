package com.crossent.monitoring.portal.common.context;

import org.springframework.core.NamedThreadLocal;

import java.util.Map;

public class ServiceContextHolder {

    private static final ThreadLocal<DefaultServiceContext> defaultServiceContext = new  NamedThreadLocal<DefaultServiceContext>("default service context") {
        protected DefaultServiceContext initialValue() {

            return new DefaultServiceContext();
        }
    };


    public static DefaultServiceContext getDefaultServiceContext() {
        return defaultServiceContext.get();
    }

    public static void clearDefaultServiceContext() {
        defaultServiceContext.remove();
    }

    public static void initDefaultServiceContext() {
        defaultServiceContext.set(new DefaultServiceContext());
    }


    public static void init(){
        initDefaultServiceContext();
    }

    public static void clear(){
        clearDefaultServiceContext();

    }
}
