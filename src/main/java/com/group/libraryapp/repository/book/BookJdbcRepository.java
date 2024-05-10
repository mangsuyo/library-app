package com.group.libraryapp.repository.book;

import com.group.libraryapp.dto.book.request.BookSaveRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookJdbcRepository {
    private  final JdbcTemplate jdbcTemplate;

    public BookJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveBook(BookSaveRequest request) {
        String sql = "INSERT INTO book(name) VALUES (?)";
        jdbcTemplate.update(sql, request.getName());
    }

}
