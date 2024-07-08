package com.loanmanagement.loanmanagementsystem.controller;

import com.loanmanagement.loanmanagementsystem.model.Loans;
import com.loanmanagement.loanmanagementsystem.service.LoanService;
import com.loanmanagement.loanmanagementsystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<Loans> createLoan(@PathVariable long userId, @RequestBody Loans loan) {
        Loans savedLoan = userService.addLoanToUser(userId, loan);
        if (savedLoan != null) {
            return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Loans>> getAllLoans() {
        List<Loans> loans = loanService.findAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loans> getLoanById(@PathVariable long loanId) {
        Loans loan = loanService.findLoanById(loanId);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{loanId}")
    public ResponseEntity<Loans> updateLoan(@PathVariable long loanId, @RequestBody Loans loanDetails) {
        Loans updatedLoan = loanService.updateLoan(loanId, loanDetails);
        if (updatedLoan != null) {
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable long loanId) {
        loanService.deleteLoan(loanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
