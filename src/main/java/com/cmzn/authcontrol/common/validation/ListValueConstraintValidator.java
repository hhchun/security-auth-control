package com.cmzn.authcontrol.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ListValueConstraintValidator implements ConstraintValidator<ListValue, String> {

    private final Set<String> set = new HashSet<>();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        String[] values = constraintAnnotation.values();
        Collections.addAll(set, values);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(value);
    }
}
