package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Max;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MaxRule implements Rule<Max, Object> {
    @Override
    public boolean check(Max annotation, Object value) {
        Class clazz = value.getClass();

        if(clazz != BigDecimal.class && clazz != BigInteger.class && clazz != Byte.class && clazz != Short.class && clazz != Integer.class && clazz != Long.class)
            return  false;

        if(clazz == double.class || clazz == float.class)
            return false;

        Long finalValue;

        if(clazz == BigDecimal.class){
            finalValue = ((BigDecimal) value).longValue();

            if(finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if(clazz == BigInteger.class){
            finalValue = ((BigInteger) value).longValue();

            if(finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if(clazz == Byte.class){
            finalValue = ((Byte) value).longValue();

            if(finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if(clazz == Short.class) {
            finalValue = ((Short) value).longValue();

            if(finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if(clazz == Integer.class) {
            finalValue = ((Integer) value).longValue();

            if(finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if(clazz == Long.class) {
            finalValue = (Long) value;

            if(finalValue > annotation.value())
                return false;
            else
                return true;
        }

        return false;
    }
}
