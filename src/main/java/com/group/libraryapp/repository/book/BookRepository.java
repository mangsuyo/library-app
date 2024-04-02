package com.group.libraryapp.repository.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.dto.book.request.BookSaveRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class BookRepository {
    private  final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveBook(BookSaveRequest request) {
        String sql = "INSERT INTO book(name) VALUES (?)";
        jdbcTemplate.update(sql, request.getName());
    }

}
