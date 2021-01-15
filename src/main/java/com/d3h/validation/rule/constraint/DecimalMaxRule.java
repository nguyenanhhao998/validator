package com.d3h.validation.rule.constraint;

        import com.d3h.validation.rule.annotation.DecimalMax;

        import java.math.BigDecimal;
        import java.math.BigInteger;
public class DecimalMaxRule implements Rule<DecimalMax> {
    @Override
    public boolean check(DecimalMax annotation, Object value) {
        Class clazz = value.getClass();

        if(clazz != BigDecimal.class && clazz != BigInteger.class && clazz != CharSequence.class && clazz != Byte.class && clazz != Short.class && clazz != Integer.class && clazz != Long.class)
            return false;

        if(clazz == double.class || clazz == float.class)
            return false;

        if(value == null)
            return true;

        if(BigDecimal.class.equals(clazz)){
            int result = ((BigDecimal) value).compareTo(new BigDecimal(annotation.value()));
            if(annotation.inclusive() && result >= 0)
                return true;
            if(!annotation.inclusive() && result > 0)
                return true;
        }

        if(BigInteger.class.equals(clazz)){
            int result = ((BigInteger) value).compareTo(new BigInteger(annotation.value()));
            if(annotation.inclusive() && result <= 0)
                return true;
            if(!annotation.inclusive() && result < 0)
                return true;
        }

        if(CharSequence.class.equals(clazz)){
            int result =(new BigDecimal((((CharSequence) value).toString()))).compareTo(new BigDecimal(annotation.value()));
            if(annotation.inclusive() && result <= 0)
                return true;
            if(!annotation.inclusive() && result < 0)
                return true;
        }

        if(Byte.class.equals(clazz)){
            int result = ((Byte) value).compareTo(Byte.valueOf(annotation.value()));
            if(annotation.inclusive() && result <= 0)
                return true;
            if(!annotation.inclusive() && result < 0)
                return true;
        }

        if(Short.class.equals(clazz)){
            int result = ((Short) value).compareTo(Short.valueOf(annotation.value()));
            if(annotation.inclusive() && result <= 0)
                return true;
            if(!annotation.inclusive() && result < 0)
                return true;
        }

        if(Integer.class.equals(clazz)){
            int result = ((Integer) value).compareTo(Integer.valueOf(annotation.value()));
            if(annotation.inclusive() && result <= 0)
                return true;
            if(!annotation.inclusive() && result < 0)
                return true;
        }

        if(Long.class.equals(clazz)){
            int result = ((Long) value).compareTo(Long.valueOf(annotation.value()));
            if(annotation.inclusive() && result <= 0)
                return true;
            if(!annotation.inclusive() && result < 0)
                return true;
        }

        return false;
    }

    @Override
    public Class<DecimalMax> getAnnotationClass() {
        return null;
    }
}
