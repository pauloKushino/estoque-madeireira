package com.pi.estoquemadeireira.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfCnpjValidator.class)
public @interface CpfCnpj {

    String message() default "cpfCnpj invalido: informe um CPF (11 digitos) ou CNPJ (14 digitos) valido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
