package com.d3h.validation.creator;

import com.d3h.validation.exception.D3HException;
import com.d3h.validation.validator.Validator;
import com.d3h.validation.violation.ConstraintViolation;
import net.sf.cglib.proxy.Enhancer;

import java.util.List;

public class D3HEnhancer extends Enhancer {
    @Override
    public Object create(Class[] argumentTypes, Object[] arguments) {
        Object newObject =  super.create(argumentTypes, arguments);
        List<ConstraintViolation> violations = Validator.getInstance().validateFields(newObject, newObject.getClass().getSuperclass());
        if(violations.size() != 0)
            throw new D3HException(violations);
        return newObject;
    }
    @Override
    public Object create() {
        Object newObject = super.create();
        List<ConstraintViolation> violations = Validator.getInstance().validateFields(newObject, newObject.getClass().getSuperclass());
        if(violations.size() != 0)
            throw new D3HException(violations);
        return newObject;
    }
}
