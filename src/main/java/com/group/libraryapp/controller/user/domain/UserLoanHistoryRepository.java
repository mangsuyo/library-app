package com.group.libraryapp.controller.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
//    select * from user_loan_history where book_name = ? and is return = ?
    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);
}
