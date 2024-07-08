	package com.loanmanagement.loanmanagementsystem.service;
	
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import com.loanmanagement.loanmanagementsystem.model.Loans;
	import com.loanmanagement.loanmanagementsystem.model.User;
	import com.loanmanagement.loanmanagementsystem.repository.LoanRepository;
	import com.loanmanagement.loanmanagementsystem.repository.UserRepository;
	
	@Service
	public class LoanService {
	
	    @Autowired
	    private LoanRepository loanRepository;
	
	    @Autowired
	    private UserRepository userRepository;
	
	    public Loans saveLoan(Long userId, Loans loan) {
	        return userRepository.findById(userId).map(user -> {
	            loan.setUser(user);
	            return loanRepository.save(loan);
	        }).orElse(null); // Handle the case when the user is not found
	    }
	
	    public List<Loans> findAllLoans() {
	        return loanRepository.findAll();
	    }
	
	    public Loans findLoanById(Long loanId) {
	        return loanRepository.findById(loanId).orElse(null);
	    }
	
	    public Loans updateLoan(Long loanId, Loans loanDetails) {
	        return loanRepository.findById(loanId).map(loan -> {
	            loan.setPrincipalAmount(loanDetails.getPrincipalAmount());
	            loan.setInterestRate(loanDetails.getInterestRate());
	            loan.setDueDate(loanDetails.getDueDate());
	            loan.setExpiryDate(loanDetails.getExpiryDate());
	            loan.setLoanType(loanDetails.getLoanType());
	            loan.setApprovedByAdmin(loanDetails.getApprovedByAdmin());
	            return loanRepository.save(loan);
	        }).orElse(null); // Handle the case when the loan is not found
	    }
	
	    public void deleteLoan(Long loanId) {
	        loanRepository.deleteById(loanId);
	    }
	
	    public List<Loans> findLoansByUserId(Long userId) {
	        return loanRepository.findByUserUserId(userId);
	    }
	}
