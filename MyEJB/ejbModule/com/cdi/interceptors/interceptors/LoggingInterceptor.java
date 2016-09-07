package com.cdi.interceptors.interceptors;


import com.cdi.interceptors.interceptorbinding.Loggingintercep;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@Loggingintercep
public class LoggingInterceptor implements Serializable {

    private static final long serialVersionUID = 8139854519874743530L;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        System.out.println("Entering method: " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}