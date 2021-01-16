package com.d3h.validation.creator;

import com.d3h.validation.enhancer.D3HEnhancer;
import com.d3h.validation.handler.Handler;
import com.d3h.validation.util.WarningUtils;
import net.sf.cglib.proxy.Enhancer;

public class CGlibClassCreator implements ICreator {

    static {
        WarningUtils.disableAccessWarnings();
    }

    private static CGlibClassCreator instance;

    private CGlibClassCreator(){
    }

    public static CGlibClassCreator getInstance(){
        if (instance == null){
            instance = new CGlibClassCreator();
        }
        return instance;
    }

    private <T> Enhancer getNewEnhancerInstance(Class<T> clazz) {
        Handler handler = new Handler();
        Enhancer enhancer = new D3HEnhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(handler);

        return enhancer;
    }

    @Override
    public <T> T create(Class<T> clazz) {
        return (T) getNewEnhancerInstance(clazz).create();
    }

    @Override
    public <T> T create(Class<T> clazz, Object[] args){
        Class<?>[] argsType = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++){
            argsType[i] = args[i].getClass();
        }
        return (T) getNewEnhancerInstance(clazz).create(argsType, args);
    }
}
