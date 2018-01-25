package com.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Controller
@RequestMapping({"view", ""})
public class MvcConfig extends WebMvcConfigurerAdapter{
	@GetMapping(value = "/home")
	public String homePage(){
		return "home";
	}
	@GetMapping(value = "/onlineOrder")
	public String onlineOrder(){
		return "onlineOrder";
	}
	@GetMapping(value="/login")
	public String loginPage(){
		return "login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)	
	public String successLogin(){
		return "admin";
	}
}
