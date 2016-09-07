package com.cdi.stateless.applicationscope;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cdi.bean.applicationscope.SoupApScope;

@Stateless
public class ChefApScope {

    @Inject
    private SoupApScope soup;

    public SoupApScope prepareSoup() {
        return soup;
    }
}