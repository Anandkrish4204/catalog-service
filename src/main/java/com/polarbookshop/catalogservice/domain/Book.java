package com.polarbookshop.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public record Book (
        @NotBlank(message = "ISBN cannot be blank")
        @Pattern(regexp = "^(\\d{10}|\\d{13})$",message = "The ISBN format must be valid")
        String isbn,

        @NotBlank(message = "Title cannot be blank")
        String title,

        @NotBlank(message = "Author name must be specified")
        String author,

        @NotNull(message="Price must be specified")
        @Positive(message = "Book price must be greater than zero")
        Double price
){ }
