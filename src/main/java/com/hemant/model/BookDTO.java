package com.hemant.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hemant.entity.Book;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private String title;

    @NotBlank(message = "Author name is required")
    private String author;

    private String price;

    public static Book map(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .price(bookDTO.getPrice())
                .build();
    }

    public static BookDTO map(Book book) {
        return BookDTO.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
    }

    public static List<BookDTO> toDTOList(List<Book> books) {
        return books.stream().map(BookDTO::map).collect(Collectors.toList());
    }

    public static List<Book> toEntityList(List<BookDTO> books) {
        return books.stream().map(BookDTO::map).collect(Collectors.toList());
    }


}
