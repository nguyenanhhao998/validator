package com.validation.creator;

import com.validation.handler.Handler;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class CGlibClassCreator implements ICreator {

    private static CGlibClassCreator instance;

    private CGlibClassCreator(){
    }

    public static CGlibClassCreator getInstance(){
        if (instance == null){
            instance = new CGlibClassCreator();
        }
        return instance;
    }


    @Override
    public <T> T create(Class<T> clazz) {
        Handler handler = new Handler();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(handler);
        return (T) enhancer.create();
    }

    @Override
    public <T> T create(Class<T> clazz, Object[] args) {
        Handler handler = new Handler();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(handler);
        Class<?>[] argsType = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++){
            argsType[i] = args[i].getClass();
        }
        return (T) enhancer.create(argsType, args);
    }
}
