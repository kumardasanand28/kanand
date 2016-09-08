package com;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Student;

@Controller
@RequestMapping("/register")
public class StudentAdmissionController {

	@RequestMapping(value="/admissionForm.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm(){
		ModelAndView mav = new ModelAndView("AdmissionForm");
		return mav;
	}
	
	
	@ModelAttribute
	public void addCommon(Model mav) {
		mav.addAttribute("headerMessage", "Engineering Colllege");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setDisallowedFields(new String[] {"studentMobile"});
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		binder.registerCustomEditor(Date.class, "studentDOB",new CustomDateEditor(dateFormat, false));
	}


	// using RequestParam
	/*@RequestMapping(value="/submitAdmissionForm.html", method=RequestMethod.POST)
	public ModelAndView submit(@RequestParam(name="studentName", defaultValue="aaa") String name,
			                   @RequestParam(name="studentHobby", defaultValue="ccc") String hobby){

		Student student = new Student();
		student.setStudentName(name);
		student.setStudentHobby(hobby);

		ModelAndView mav = new ModelAndView("AdmissionSuccess");
		mav.addObject("message", "Details Submitted by you Successfully");
		mav.addObject("student", student);
		return mav;
	}*/

	//using @ModelAttribute
	@RequestMapping(value="/submitAdmissionForm.html", method=RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("student") Student student, BindingResult result){
		
		if(result.hasErrors()){
			ModelAndView mav = new ModelAndView("AdmissionForm");
			return mav;
		}

		ModelAndView mav = new ModelAndView("AdmissionSuccess");
		mav.addObject("message", "Details Submitted by you Successfully");
		return mav;
	}



}
