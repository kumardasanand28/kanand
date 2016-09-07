package com.cdi.stateless;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cdi.bean.Soup;

@Stateless
public class Chef {

    @Inject
    private Soup soup;

    public Soup prepareSoup() {
        return soup;
    }
}