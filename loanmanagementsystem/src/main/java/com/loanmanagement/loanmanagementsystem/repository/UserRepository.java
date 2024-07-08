package com.loanmanagement.loanmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanmanagement.loanmanagementsystem.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}	