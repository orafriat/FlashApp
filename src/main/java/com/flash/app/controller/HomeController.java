package com.flash.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class HomeController {
	
	

	@RequestMapping(value = { "/", "/home", "/home.html" })
	public ModelAndView home() {
		ModelAndView response = new ModelAndView();
		response.setViewName("home");
		return response;
	}

}
