package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ApplicationContainer {
	
	
	@RequestMapping(value="/")
	public ModelAndView showForm() {
		  return new ModelAndView("index");
		 }

}
