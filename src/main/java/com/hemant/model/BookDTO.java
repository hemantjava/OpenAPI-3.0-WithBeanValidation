package com.hemant.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hemant.entity.Book;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private String title;

    @NotBlank(message = "Author name is required")
    private String author;

    private String price;

    public static Book toBook(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .price(bookDTO.getPrice())
                .build();
    }
}
