package com.hemant;

import com.hemant.entity.Book;
import com.hemant.repository.BookRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book API", version = "1.0"),tags = @Tag(name = "Book", description = "Book API Documentation"),
    externalDocs = @ExternalDocumentation(description = "Find more info here", url = "https://github.com/hemantjava"))

@Log4j2
@AllArgsConstructor
public class OpenapiDocumentationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiDocumentationApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(BookRepository bookRepository) {
        return (args) -> {
            bookRepository.save(Book.builder().title("Comic").author("Harry Potter").price("2000").build());
            bookRepository.save(Book.builder().title("Comic").author("hemant sahu").price("2000").build());
            log.info("Book count: " + bookRepository.count());
        };
    }
}