package com.group.libraryapp.service.book;


import com.group.libraryapp.dto.book.request.BookSaveRequest;
import com.group.libraryapp.repository.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void saveBook(BookSaveRequest request) {
        bookRepository.saveBook(request);
    }
}
