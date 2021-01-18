package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Digits;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DigitsRule implements Rule<Digits, Object> {
    @Override
    public boolean check(Digits annotation, Object value) {
        if (value == null)
            return true;

        if (!(value instanceof BigDecimal) && !(value instanceof BigInteger) && !(value instanceof CharSequence) && !(value instanceof Byte) && !(value instanceof Short) && !(value instanceof Integer) && !(value instanceof Long))
            return false;


        if (value instanceof BigDecimal) {
            BigDecimal n = ((BigDecimal) value);
            int integer = n.signum() == 0 ? 1 : n.precision() - n.scale();
            int frac = Math.max(0, n.stripTrailingZeros().scale());

            if (integer <= annotation.integer() && frac <= annotation.fraction())
                return true;
        }

        if (value instanceof BigInteger) {
            BigInteger n = ((BigInteger) value);
            int integer = n.toString().length();

            if (integer <= annotation.integer())
                return true;
        }

        if (value instanceof CharSequence) {
            BigDecimal n = new BigDecimal(((CharSequence) value).toString());
            int integer = n.signum() == 0 ? 1 : n.precision() - n.scale();
            int frac = Math.max(0, n.stripTrailingZeros().scale());

            if (integer <= annotation.integer() && frac <= annotation.fraction())
                return true;
        }

        if (value instanceof Byte) {
            BigDecimal n = new BigDecimal(((Byte) value).toString());
            int integer = n.signum() == 0 ? 1 : n.precision() - n.scale();
            int frac = Math.max(0, n.stripTrailingZeros().scale());

            if (integer <= annotation.integer() && frac <= annotation.fraction())
                return true;
        }

        if (value instanceof Short) {
            int integer = ((Short) value).toString().length();
            if (integer <= annotation.integer())
                return true;
        }

        if (value instanceof Integer) {
            int integer = ((Integer) value).toString().length();
            if (integer <= annotation.integer())
                return true;
        }

        if (value instanceof Long) {
            int integer = ((Long) value).toString().length();
            if (integer <= annotation.integer())
                return true;
        }

        return false;
    }
}
