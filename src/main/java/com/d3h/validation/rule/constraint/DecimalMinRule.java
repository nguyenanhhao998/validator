package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.DecimalMin;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DecimalMinRule implements Rule<DecimalMin, Object> {
    @Override
    public boolean check(DecimalMin annotation, Object value) {
        if (value == null)
            return true;

        if (!(value instanceof BigDecimal) && !(value instanceof BigInteger) && !(value instanceof CharSequence) && !(value instanceof Byte) && !(value instanceof Short) && !(value instanceof Integer) && ! (value instanceof Long))
            return false;

        if (value instanceof Double || value instanceof Float)
            return false;

        if (value instanceof BigDecimal) {
            int result = ((BigDecimal) value).compareTo(new BigDecimal(annotation.value()));
            if (annotation.inclusive() && result >= 0)
                return true;
            if (!annotation.inclusive() && result > 0)
                return true;
        }

        if (value instanceof BigInteger) {
            int result = ((BigInteger) value).compareTo(new BigInteger(annotation.value()));
            if (annotation.inclusive() && result >= 0)
                return true;
            if (!annotation.inclusive() && result > 0)
                return true;
        }

        if (value instanceof CharSequence) {
            int result = (new BigDecimal((((CharSequence) value).toString()))).compareTo(new BigDecimal(annotation.value()));
            if (annotation.inclusive() && result >= 0)
                return true;
            if (!annotation.inclusive() && result > 0)
                return true;
        }

        if (value instanceof Byte) {
            int result = ((Byte) value).compareTo(Byte.valueOf(annotation.value()));
            if (annotation.inclusive() && result >= 0)
                return true;
            if (!annotation.inclusive() && result > 0)
                return true;
        }

        if (value instanceof Short) {
            int result = ((Short) value).compareTo(Short.valueOf(annotation.value()));
            if (annotation.inclusive() && result >= 0)
                return true;
            if (!annotation.inclusive() && result > 0)
                return true;
        }

        if (value instanceof Integer) {
            int result = ((Integer) value).compareTo(Integer.valueOf(annotation.value()));
            if (annotation.inclusive() && result >= 0)
                return true;
            if (!annotation.inclusive() && result > 0)
                return true;
        }

        if (value instanceof Long) {
            int result = ((Long) value).compareTo(Long.valueOf(annotation.value()));
            if (annotation.inclusive() && result >= 0)
                return true;
            if (!annotation.inclusive() && result > 0)
                return true;
        }

        return false;
    }
}
