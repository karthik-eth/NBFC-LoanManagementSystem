package com.loanmanagement.loanmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.loanmanagement.loanmanagementsystem.model.User;
import com.loanmanagement.loanmanagementsystem.model.Loans;
import com.loanmanagement.loanmanagementsystem.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // User Operations
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> partialUpdateUser(@PathVariable long id, @RequestBody User userDetails) {
        User updatedUser = userService.partialUpdateUser(id, userDetails);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Loan operations
    @GetMapping("/{userId}/loans")
    public ResponseEntity<List<Loans>> getUserLoans(@PathVariable long userId) {
        List<Loans> loans = userService.findLoansByUserId(userId);
        if (loans != null && !loans.isEmpty()) {
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/loans")
    public ResponseEntity<Loans> createLoanForUser(@PathVariable long userId, @RequestBody Loans loan) {
        Loans newLoan = userService.addLoanToUser(userId, loan);
        if (newLoan != null) {
            return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}/loans/{loanId}")
    public ResponseEntity<Loans> getLoanByIdForUser(@PathVariable long userId, @PathVariable long loanId) {
        Loans loan = userService.findLoanByIdAndUserId(loanId, userId);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/loans/{loanId}")
    public ResponseEntity<Loans> updateLoanForUser(@PathVariable long userId, @PathVariable long loanId, @RequestBody Loans loanDetails) {
        Loans updatedLoan = userService.updateLoan(userId, loanId, loanDetails);
        if (updatedLoan != null) {
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/loans/{loanId}")
    public ResponseEntity<Void> deleteLoanForUser(@PathVariable long userId, @PathVariable long loanId) {
        userService.deleteLoan(userId, loanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
