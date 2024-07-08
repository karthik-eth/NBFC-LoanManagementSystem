package com.loanmanagement.loanmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import org.springframework.stereotype.Service;

import com.loanmanagement.loanmanagementsystem.exception.ResourceNotFoundException;
import com.loanmanagement.loanmanagementsystem.model.User;
import com.loanmanagement.loanmanagementsystem.model.Loans;
import com.loanmanagement.loanmanagementsystem.repository.UserRepository;
import com.loanmanagement.loanmanagementsystem.repository.LoanRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    // User methods

    public User saveUser(User user) {
        final String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (!userDetailsManager.userExists(user.getUsername())) {
            org.springframework.security.core.userdetails.UserDetails newUser = org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(encodedPassword)
                    .roles(user.getRole())
                    .build();
            userDetailsManager.createUser(newUser);
        }

        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
            .map(user -> {
                user.setName(userDetails.getName());
                user.setUsername(userDetails.getUsername());
                if (userDetails.getPassword() != null) {
                    final String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
                    user.setPassword(encodedPassword);

                    if (!userDetailsManager.userExists(user.getUsername())) {
                        org.springframework.security.core.userdetails.UserDetails newUser = org.springframework.security.core.userdetails.User
                                .withUsername(user.getUsername())
                                .password(encodedPassword)
                                .roles(user.getRole())
                                .build();
                        userDetailsManager.createUser(newUser);
                    }
                }
                user.setDob(userDetails.getDob());
                user.setAddress(userDetails.getAddress());
                user.setOccupation(userDetails.getOccupation());
                user.setNetWorth(userDetails.getNetWorth());
                user.setContactNumber(userDetails.getContactNumber());
                user.setEmail(userDetails.getEmail());
                user.setGender(userDetails.getGender());
                user.setRole(userDetails.getRole());
                return userRepository.save(user);
            })
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User partialUpdateUser(Long id, User userDetails) {
        return userRepository.findById(id)
            .map(user -> {
                if (userDetails.getName() != null) user.setName(userDetails.getName());
                if (userDetails.getPassword() != null) {
                    final String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
                    user.setPassword(encodedPassword);

                    if (!userDetailsManager.userExists(user.getUsername())) {
                        org.springframework.security.core.userdetails.UserDetails newUser = org.springframework.security.core.userdetails.User
                                .withUsername(user.getUsername())
                                .password(encodedPassword)
                                .roles(user.getRole())
                                .build();
                        userDetailsManager.createUser(newUser);
                    }
                }
                if (userDetails.getDob() != null) user.setDob(userDetails.getDob());
                if (userDetails.getAddress() != null) user.setAddress(userDetails.getAddress());
                if (userDetails.getOccupation() != null) user.setOccupation(userDetails.getOccupation());
                if (userDetails.getNetWorth() != null) user.setNetWorth(userDetails.getNetWorth());
                if (userDetails.getContactNumber() != null) user.setContactNumber(userDetails.getContactNumber());
                if (userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());
                if (userDetails.getGender() != null) user.setGender(userDetails.getGender());
                if (userDetails.getRole() != null) user.setRole(userDetails.getRole());
                return userRepository.save(user);
            })
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException("User not found with id: " + id);
        userRepository.deleteById(id);
    }

    // Loan methods

    public List<Loans> findLoansByUserId(Long userId) {
        return loanRepository.findByUserUserId(userId);
    }

    public Loans addLoanToUser(Long userId, Loans loan) {
        return userRepository.findById(userId).map(user -> {
            loan.setUser(user);
            return loanRepository.save(loan);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    public Loans findLoanByIdAndUserId(Long loanId, Long userId) {
        return loanRepository.findById(loanId)
            .filter(loan -> loan.getUser().getUserId().equals(userId))
            .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + loanId));
    }

    public Loans updateLoan(Long userId, Long loanId, Loans loanDetails) {
        if (!userRepository.existsById(userId)) throw new ResourceNotFoundException("User not found with id: " + userId);
        return loanRepository.findById(loanId)
            .map(loan -> {
                loan.setPrincipalAmount(loanDetails.getPrincipalAmount());
                loan.setInterestRate(loanDetails.getInterestRate());
                loan.setDueDate(loanDetails.getDueDate());
                loan.setExpiryDate(loanDetails.getExpiryDate());
                loan.setLoanType(loanDetails.getLoanType());
                loan.setApprovedByAdmin(loanDetails.getApprovedByAdmin());
                return loanRepository.save(loan);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + loanId));
    }

    public void deleteLoan(Long userId, Long loanId) {
        if (!userRepository.existsById(userId)) throw new ResourceNotFoundException("User not found with id: " + userId);
        if (!loanRepository.existsById(loanId)) throw new ResourceNotFoundException("Loan not found with id: " + loanId);
        loanRepository.deleteById(loanId);
    }
}
