package de.rgse.brewlogs.services;

import javax.inject.Qualifier;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.*;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Validate
@Interceptor
public class ValidatorInterceptor  {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @AroundInvoke
    public Object intercept(InvocationContext invocationContext) throws  Exception {

        Parameter[] parameters = invocationContext.getMethod().getParameters();

        Set<ConstraintViolation<Object>> violations = new HashSet<>();

        if(parameters != null) {
            for(int i = 0; i < parameters.length; i++) {
                Parameter paramater = parameters[i];
                if(paramater.isAnnotationPresent(Validatable.class)) {
                    violations.addAll(validator.validate(invocationContext.getParameters()[i]));
                }
            }
        }

        if(violations.isEmpty()) {
            return invocationContext.proceed();

        } else {
            throw new ConstraintViolationException(violations);
        }
    }
}
