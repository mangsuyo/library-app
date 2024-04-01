package com.group.libraryapp.controller.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.dto.book.request.BookSaveRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    List<Book> books = new ArrayList<>();

    @PostMapping("/book")
    public void saveBook(@RequestBody BookSaveRequest request) {
        books.add(new Book(request.getName()));
    }


}
