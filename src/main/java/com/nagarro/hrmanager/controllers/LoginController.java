package com.nagarro.hrmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.hrmanager.service.LoginService;

@Controller
public class LoginController {
	
	@GetMapping("/Login")
	public String Login() {
		
		System.out.println("login page");
		return "LoginForm";
	}
	
	@Autowired
	public LoginService loginService;
	
	//checkUser method
	@PostMapping("/employeeList")
	public ModelAndView checkUser(@RequestParam("userId") int userId,
			@RequestParam("password") String userPassword) {

		//create the object of ModelAndView
		ModelAndView modelView = new ModelAndView();
		//add username
		modelView.addObject("userId",userId);
		//add user password
		modelView.addObject("password",userPassword);
		//store the check login menthod into the userExists variable
		boolean userExists = loginService.checkLogin(userId, userPassword);
		
		
		if(userExists) {
			
			modelView.setViewName("redirect:/employeeList");
		}else {
			//redirect to the login form page
			modelView.setViewName("LoginForm");
			//error message of invalid credentials
			modelView.addObject("errorMessage","invalid credentials");


		}
		return modelView;
	}
	
	
	


}
