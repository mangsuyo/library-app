package com.group.libraryapp.service.book;


import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.dto.book.request.BookSaveRequest;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
    }

    public void saveBook(BookSaveRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    public void loanBook(BookLoanRequest request) {
        Book book = bookRepository.findByName(request.getBookName());
        if (book == null) {
            throw new IllegalArgumentException();
        }
        boolean isNotReturn = userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(), false);
        if (isNotReturn) {
            throw new IllegalArgumentException("이미 대출된 책입니다.");
        }
        userLoanHistoryRepository.save(new UserLoanHistory(request.getUserName(), request.getBookName()));
    }

    public void returnBook(BookReturnRequest request){
        Book book = bookRepository.findByName(request.getBookName());
        if (book == null) {
            throw new IllegalArgumentException();
        }
        boolean isReturn = userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(), true);
        if (isReturn) {
            throw new IllegalArgumentException("이미 반납된 책입니다.");
        }
        UserLoanHistory userLoanHistory = userLoanHistoryRepository.findByUserNameAndBookName(request.getUserName(), request.getBookName());
        if(userLoanHistory == null){
            throw new IllegalArgumentException();
        }
        userLoanHistory.doReturn();
    }
}
