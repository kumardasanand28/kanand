package com.student.controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.student.bean.Student;
import com.student.entity.UserEntity;
import com.student.repository.UserRepository;
import com.student.service.RegistrationService;

@Controller
@RequestMapping("/register")
public class StudentController {
	
	@RequestMapping(value="/admissionForm.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm(){
		ModelAndView mav = new ModelAndView("AdmissionForm");
		return mav;
	}
	
	
	@RequestMapping(value="/submitAdmissionForm.html", method=RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("student") Student student, BindingResult result){
		
		if(result.hasErrors()){
			ModelAndView mav = new ModelAndView("AdmissionForm");
			return mav;
		}
		
		String json = new Gson().toJson(student);
		
		ClassPathXmlApplicationContext contextAno = new ClassPathXmlApplicationContext("/WEB-INF/sample-servlet.xml");
		RegistrationService reg = contextAno.getBean(RegistrationService.class);
		
		reg.sendMessageAsJSON(json);

		ModelAndView mav = new ModelAndView("AdmissionSuccess");
		UserRepository user = (UserRepository) contextAno.getBean(UserRepository.class);
		List<UserEntity> userList = user.selectAll();
		mav.addObject("resultList", userList);
		mav.addObject("message", "Details Submitted by you Successfully");
		return mav;
	}

}
