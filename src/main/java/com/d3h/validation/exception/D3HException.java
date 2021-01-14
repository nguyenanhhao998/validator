package com.d3h.validation.exception;

import com.d3h.validation.violation.ConstraintViolation;

import java.util.List;

public class D3HException extends RuntimeException {
    List<ConstraintViolation> constraintViolations;

    public D3HException(List<ConstraintViolation> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public List<ConstraintViolation> getConstraintViolations() {
        return constraintViolations;
    }
}
