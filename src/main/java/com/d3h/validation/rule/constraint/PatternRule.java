package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Pattern;

public class PatternRule implements Rule<Pattern> {
    @Override
    public boolean check(Pattern annotation, Object value) {
        if(!(value instanceof CharSequence))
            return false;

        boolean result = java.util.regex.Pattern.matches(annotation.regexp(), (CharSequence) value);
        return result;
    }

    @Override
    public Class<Pattern> getAnnotationClass() {
        return null;
    }
}
