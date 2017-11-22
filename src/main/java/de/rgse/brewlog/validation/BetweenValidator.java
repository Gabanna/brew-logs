package de.rgse.brewlog.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BetweenValidator implements ConstraintValidator<Between, Integer> {

	private int from;
	private int to;

	@Override
	public void initialize(Between constraintAnnotation) {
		from = constraintAnnotation.from();
		to = constraintAnnotation.to();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value >= from && value <= to;
	}

}
