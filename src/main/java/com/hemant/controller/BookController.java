package com.hemant.controller;

import com.hemant.entity.Book;
import com.hemant.exception.BookIdMismatchException;
import com.hemant.exception.BookNotFoundException;
import com.hemant.model.BookDTO;
import com.hemant.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/data")
@AllArgsConstructor
public class BookController {

    private BookRepository bookRepository;

    @Operation(summary = "This is to fetch All the Books stored in db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("book/")
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @Operation(summary = "This is to new Book data stored in db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("book/")
    public Book create(@RequestBody @Valid BookDTO book) {
        return bookRepository.save(BookDTO.toBook(book));
    }

    @Operation(summary = "This is to delete book data by id from db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("book/{id}")
    public void delete(@PathVariable long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    @Operation(summary = "This is to update  data by id from db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("book/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }


    @Operation(summary = "This is to find book data by id from db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("book/{id}")
    public Book find(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found: " + id));
    }


}
