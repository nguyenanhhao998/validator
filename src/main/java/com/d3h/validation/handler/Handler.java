package com.d3h.validation.handler;

import com.d3h.validation.exception.D3HException;
import com.d3h.validation.validator.Validator;
import com.d3h.validation.violation.ConstraintViolation;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

public class Handler implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws D3HException {
        List<ConstraintViolation> constraintViolations;

        try {
            constraintViolations = Validator.getInstance().validateMethodParameters(o, method, args);

            if (constraintViolations.size() != 0) {
                throw new D3HException(constraintViolations);
            }

            Object returnValue = methodProxy.invokeSuper(o, args);
            constraintViolations = Validator.getInstance().validateMethodReturnValue(method, returnValue);

            if (constraintViolations.size() != 0) {
                throw new D3HException(constraintViolations);
            }

            constraintViolations = Validator.getInstance().validate(o);

            if (constraintViolations.size() != 0) {
                throw new D3HException(constraintViolations);
            }

            return returnValue;
        } catch (D3HException d3HException) {
            throw d3HException;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
