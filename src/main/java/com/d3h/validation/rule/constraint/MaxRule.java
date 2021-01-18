package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Max;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MaxRule implements Rule<Max, Object> {
    @Override
    public boolean check(Max annotation, Object value) {

        if (value == null) {
            return true;
        }

        if (!(value instanceof BigDecimal) && ! (value instanceof BigInteger) && !(value instanceof Byte) && !(value instanceof Short) && !(value instanceof Integer) && !(value instanceof Long))
            return false;

        if (value instanceof Double || value instanceof Float)
            return false;

        Long finalValue;

        if (value instanceof BigDecimal) {
            finalValue = ((BigDecimal) value).longValue();

            if (finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if (value instanceof BigInteger) {
            finalValue = ((BigInteger) value).longValue();

            if (finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if (value instanceof Byte) {
            finalValue = ((Byte) value).longValue();

            if (finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if (value instanceof Short) {
            finalValue = ((Short) value).longValue();

            if (finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if (value instanceof Integer) {
            finalValue = ((Integer) value).longValue();

            if (finalValue > annotation.value())
                return false;
            else
                return true;
        }

        if (value instanceof Long) {
            finalValue = (Long) value;

            if (finalValue > annotation.value())
                return false;
            else
                return true;
        }

        return false;
    }
}
