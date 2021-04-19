package com.example.spring_rest_books.web;

import com.example.spring_rest_books.entity.Book;
import com.example.spring_rest_books.repository.BookRepository;
import com.example.spring_rest_books.repository.BookSearchSpecification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAll (@RequestParam(required = false) String bookTitle) {
        return bookRepository.findAll(new BookSearchSpecification(bookTitle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook (@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);

        return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book,
                                       UriComponentsBuilder ucBuilder) {
        Book newBook = bookRepository.save(book);

        return ResponseEntity.created(
                ucBuilder.path("/books/{id}").buildAndExpand(newBook.getId()).toUri()
        ).build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
