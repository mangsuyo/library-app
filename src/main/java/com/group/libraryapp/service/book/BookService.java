package com.group.libraryapp.service.book;

import com.group.libraryapp.controller.book.domain.Book;
import com.group.libraryapp.controller.book.domain.BookRepository;
import com.group.libraryapp.controller.user.domain.User;
import com.group.libraryapp.controller.user.domain.UserLoanHistory;
import com.group.libraryapp.controller.user.domain.UserLoanHistoryRepository;
import com.group.libraryapp.controller.user.domain.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {

        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("이미 대출되어 있는 책입니다.");
        }

        User user = userRepository.findByName(request.getUserName());
        if (user == null) {
            throw new IllegalArgumentException();
        }
        user.loanBook(book.getName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName());
        if (user == null) {
            throw new IllegalArgumentException();
        }
       user.returnBook(request.getBookName());
        // userLoanHistoryRepository.save(userLoanHistory);
        // 가져온 Entity에 대해서 변화 자동감지하여 저장함
    }

}
