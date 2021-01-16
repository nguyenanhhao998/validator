package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Digits;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DigitsRule implements Rule<Digits> {
    @Override
    public boolean check(Digits annotation, Object value) {
        Class clazz = value.getClass();

        if(clazz != BigDecimal.class && clazz != BigInteger.class && !(value instanceof CharSequence) && clazz != Byte.class && clazz != Short.class && clazz != Integer.class && clazz != Long.class)
            return false;

        if(value == null) return true;

        if(BigDecimal.class.equals(clazz)){
            BigDecimal n = ((BigDecimal) value);
            int integer = n.signum() == 0 ? 1 : n.precision() - n.scale();
            int frac = Math.max(0, n.stripTrailingZeros().scale());

            if(integer <= annotation.integer() && frac <= annotation.fraction())
                return true;
        }

        if(BigInteger.class.equals(clazz)){
            BigInteger n = ((BigInteger) value);
            int integer = n.toString().length();

            if(integer <= annotation.integer())
                return true;
        }

        if(value instanceof CharSequence){
            BigDecimal n = new BigDecimal(((CharSequence) value).toString());
            int integer = n.signum() == 0 ? 1 : n.precision() - n.scale();
            int frac = Math.max(0, n.stripTrailingZeros().scale());

            if(integer <= annotation.integer() && frac <= annotation.fraction())
                return true;
        }

        if(Byte.class.equals(clazz)){
            BigDecimal n = new BigDecimal(((Byte) value).toString());
            int integer = n.signum() == 0 ? 1 : n.precision() - n.scale();
            int frac = Math.max(0, n.stripTrailingZeros().scale());

            if(integer <= annotation.integer() && frac <= annotation.fraction())
                return true;
        }

        if(Short.class.equals(clazz)){
            int integer = ((Short) value).toString().length();
            if(integer <= annotation.integer())
                return true;
        }

        if(Integer.class.equals(clazz)){
            int integer = ((Integer) value).toString().length();
            if(integer <= annotation.integer())
                return true;
        }

        if(Long.class.equals(clazz)){
            int integer = ((Long) value).toString().length();
            if(integer <= annotation.integer())
                return true;
        }

        return false;
    }

    @Override
    public Class<Digits> getAnnotationClass() {
        return null;
    }
}
