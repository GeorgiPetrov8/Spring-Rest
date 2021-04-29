package com.example.spring_rest_books.repository;

import com.example.spring_rest_books.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;

public class BookSearchSpecification implements Specification<Book> {

    private final String bookTitle;

    public BookSearchSpecification(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public Predicate toPredicate(Root<Book> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.conjunction();

        if (bookTitle != null) {
            p.getExpressions().add(
            criteriaBuilder.and(criteriaBuilder.equal(root.get("title"), bookTitle)));
        }

        return p;
    }
}
