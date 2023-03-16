package com.cmzn.authcontrol.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class ListValueIntConstraintValidator implements ConstraintValidator<ListValueInt, Number> {

    private final Set<Number> set = new HashSet<>();

    @Override
    public void initialize(ListValueInt constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        for (int value : values) {
            set.add(value);
        }
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(value);
    }
}
