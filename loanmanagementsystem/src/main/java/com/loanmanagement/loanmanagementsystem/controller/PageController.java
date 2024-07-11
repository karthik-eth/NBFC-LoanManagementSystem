package com.loanmanagement.loanmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class PageController {

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/userlogin")
    public String showLoginPage() {
        return "userlogin";
    }
    
    @GetMapping("/about")
    public String  showAboutus() {
    	return "about"; 
    }

    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }

    @GetMapping("/adminlogin")
    public String showAdminLoginPage() {
        return "adminlogin";
    }

    @GetMapping("/user-profile")
    public String showUserProfilePage() {
        return "user-profile";
    }
    
    @GetMapping("/userdashboard")
    public String showUserDashboard(){
        return "userdashboard";
    }
    

    @GetMapping("/loans-page")
    public String showLoansPage() {
        return "loans";
    }

    @GetMapping("/loan/details")
    public String showLoanDetailsPage() {
        return "loandetails";
    }

    @GetMapping("/apply/loan")
    public String showApplyLoanPage() {
        return "applyloan";
    }

    @GetMapping("/edit/loan")
    public String showEditLoanPage() {
        return "editloan";
    }

    @GetMapping("/admindashboard")
    public String showAdminDashboardPage() {
        return "admindashboard";
    }
 
    @GetMapping("/error/404")
    public String show404Page() {
        return "error404notfound";
    }

}
