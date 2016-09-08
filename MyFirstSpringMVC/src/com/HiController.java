package com;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/start")
public class HiController {

	@RequestMapping("/welcome/{userName}/{countryName}")
	public ModelAndView hello(){
		ModelAndView mav = new ModelAndView("hello");
		mav.addObject("msg", "Hello World!!!!!!!!!!!!!");
		return mav;
	}


	@RequestMapping("/welcomeanand/{userName}/{countryName}")
	public ModelAndView helloAnand(@PathVariable Map<String, String> pathVars){
		
		String name = pathVars.get("userName");
		String country = pathVars.get("countryName");
		
		ModelAndView mav = new ModelAndView("hello");
		mav.addObject("msg", "Hello Anand :  "+name+"  "+"From the coutry :"+country);
		return mav;
	}



}
