package com.eidiko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
@Scope("prototype")
public class EmailContext {

    private final Context context = new Context();

    @Autowired
    public EmailContext() {
    }

    public void setVariable(String name, Object value) {
        context.setVariable(name, value);
        
    }

    public Context getContext() {
        return context;
    }
}
