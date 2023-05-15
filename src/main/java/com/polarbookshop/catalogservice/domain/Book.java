package com.polarbookshop.catalogservice.domain;

import org.springframework.data.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.Instant;

public record Book (

        @Id
        Long id,

        @NotBlank(message = "ISBN cannot be blank")
        @Pattern(regexp = "^(\\d{10}|\\d{13})$",message = "The ISBN format must be valid")
        String isbn,

        @NotBlank(message = "Title cannot be blank")
        String title,

        @NotBlank(message = "Author name must be specified")
        String author,

        @NotNull(message="Price must be specified")
        @Positive(message = "Book price must be greater than zero")
        Double price,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

        @Version
        int version
){
        public static Book of(String isbn, String title, String author,Double price){
                return new Book(null,isbn,title,author,price,null,null,0);
        }
}
