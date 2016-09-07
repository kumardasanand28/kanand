package com.cdi.interceptors.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import com.cdi.interceptors.interceptorbinding.Loggingintercep;

@Loggingintercep
@Stateful
public class BookShow implements Serializable {

    private static final long serialVersionUID = 6350400892234496909L;

    public List<String> getMoviesList() {
        List<String> moviesAvailable = new ArrayList<String>();
        moviesAvailable.add("12 Angry Men");
        moviesAvailable.add("Kings speech");
        return moviesAvailable;
    }

    public Integer getDiscountedPrice(int ticketPrice) {
        return ticketPrice - 50;
    }
    
    
    //this is a internal interceptor
   /* @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        System.out.println("Entering method: " + ctx.getMethod().getName());
        return ctx.proceed();
    }*/
}