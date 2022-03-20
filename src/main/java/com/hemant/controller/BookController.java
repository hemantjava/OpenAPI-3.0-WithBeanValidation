package com.hemant.controller;

import com.hemant.entity.Book;
import com.hemant.exception.BookNotFoundException;
import com.hemant.model.BookDTO;
import com.hemant.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController("/data")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

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
    public List<BookDTO> allBooks() {
        return BookDTO.toDTOList(bookRepository.findAll());
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
    public BookDTO create(@RequestBody @Valid BookDTO book) {
        Book save = bookRepository.save(BookDTO.map(book));
        return BookDTO.map(save);
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
    public BookDTO updateBook(@RequestBody BookDTO book, @PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return Optional.of(bookRepository.save(BookDTO.map(book)))
                .map(BookDTO::map).orElseThrow(BookNotFoundException::new);
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
    public BookDTO find(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(BookDTO::map)
                .orElseThrow(() -> new BookNotFoundException("Book not found: " + id));
    }


}
