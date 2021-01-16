package com.d3h.validation.Enhancer;

import com.d3h.validation.exception.D3HException;
import com.d3h.validation.validator.Validator;
import com.d3h.validation.violation.ConstraintViolation;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class D3HEnhancer extends Enhancer {
    @Override
    public Object create(Class[] argumentTypes, Object[] arguments) {
        try {
            Class superClass = getSuperClass();
            Constructor constructor = superClass.getConstructor(argumentTypes);
            List<ConstraintViolation> violations = Validator.getInstance().validateConstructor(constructor, arguments);
            if(violations.size() != 0)
                throw new D3HException(violations);

            Object newObject =  super.create(argumentTypes, arguments);
            violations = Validator.getInstance().validateFields(newObject, newObject.getClass().getSuperclass());
            if(violations.size() != 0)
                throw new D3HException(violations);
            return newObject;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Object create() {
        Object newObject = super.create();
        List<ConstraintViolation> violations = Validator.getInstance().validateFields(newObject, newObject.getClass().getSuperclass());
        if(violations.size() != 0)
            throw new D3HException(violations);
        return newObject;
    }

    private Class getSuperClass(){
        try {
            Field field = this.getClass().getSuperclass().getDeclaredField("superclass");
            field.setAccessible(true);
            Class superClass = (Class)field.get(this);
            return superClass;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
