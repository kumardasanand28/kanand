package com.student.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.student.bean.Student;
import com.student.configuration.DIConfiguration;
import com.student.entity.UserEntity;
import com.student.service.RegistrationService;
import com.student.springjpa.service.UserService;

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
		
		ApplicationContext contextAno = new AnnotationConfigApplicationContext(DIConfiguration.class);
		RegistrationService reg = contextAno.getBean(RegistrationService.class);
		
		reg.sendMessageAsJSON(json);

		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("AdmissionSuccess");
		//UserRepository user = (UserRepository) contextAno.getBean(UserRepository.class);
		//List<UserEntity> userList = user.selectAll();
		UserService service = (UserService)contextAno.getBean(UserService.class);
		List<UserEntity> userList = service.findAll();
		
		
		UserEntity user = userList.get(0);
		UserEntity userFoundById =  service.findById(user.getId());
		List<UserEntity> userFoundByNameOrHobby =  service.findByNameOrHobby(userFoundById.getStudentName(), userFoundById.getStudentHobby());
		List<UserEntity> userFoundByHobby = service.findByHobby(userFoundById.getStudentHobby());
		//service.delete(user);
		//userList.remove(user);
	
		
		
		mav.addObject("resultList", userList);
		mav.addObject("message", "Details Submitted by you Successfully");
		return mav;
	}

}
