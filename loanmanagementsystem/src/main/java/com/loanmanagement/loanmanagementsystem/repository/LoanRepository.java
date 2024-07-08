package com.loanmanagement.loanmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loanmanagement.loanmanagementsystem.model.Loans;
import com.loanmanagement.loanmanagementsystem.model.User;

public interface LoanRepository extends JpaRepository<Loans, Long> {
    List<Loans> findByUserUserId(Long userId);

    List<Loans> findByApprovedByAdmin(Boolean approved);

}
