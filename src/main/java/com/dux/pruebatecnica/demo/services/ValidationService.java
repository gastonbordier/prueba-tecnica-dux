package com.dux.pruebatecnica.demo.services;

import com.dux.pruebatecnica.demo.exceptions.ValidationFailedException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService<T> {
    private final Validator validator;

    public void validate(Object objectToValidate) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(objectToValidate);
        if (!constraintViolations.isEmpty())
            throw new ValidationFailedException(constraintViolations);
    }

}
