package com.polarbookshop.catalogservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void before(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validationSuccess(){
        var book = Book.of("1234567890","Wings of fire","Abdul",100.00);
        Set<ConstraintViolation<Book>> validate = validator.validate(book);
        Assertions.assertThat(validate).isEmpty();
    }

    @Test
    void validateViolations(){
        var book = Book.of("12345678903","Wings of fire","Abdul",100.00);
        Set<ConstraintViolation<Book>> validate = validator.validate(book);
        Assertions.assertThat(validate).hasSize(1);
        Assertions.assertThat(validate.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid");
    }
}
