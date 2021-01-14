package com.d3h.validation.rule.constraint.Composite;

import com.d3h.validation.Enhancer.D3HEnhancer;
import net.sf.cglib.proxy.Enhancer;

public class Creator<T extends RuleComposite> {
    private static Creator instance;

    private Creator(){
    }

    public static Creator getInstance(){
        if (instance == null){
            instance = new Creator();
        }
        return instance;
    }

    private  <T> Enhancer getNewEnhancerInstance(Class<T> clazz) {
        CompositeHandler handler = new CompositeHandler();
        Enhancer enhancer = new D3HEnhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(handler);

        return enhancer;
    }

    public <T>  T create(Class<T> clazz) {
        return (T) getNewEnhancerInstance(clazz).create();
    }
}
