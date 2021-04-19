package com.example.spring_rest_books.init;

import com.example.spring_rest_books.entity.Book;
import com.example.spring_rest_books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInit implements CommandLineRunner {
    private final BookRepository bookRepository;

    @Autowired
    public DataInit(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.saveAll(this.initBooks());
    }

    private List<Book> initBooks() {
        return List.of(
                new Book("Harry Potter", "J. K. Rowling", "2313123-1232131"),
                new Book("It", "Steven King", "1312321-3123213"),
                new Book("Game of Thrones", "George R. R. Martin", "31232131-2131232155")
                );
    }
}
