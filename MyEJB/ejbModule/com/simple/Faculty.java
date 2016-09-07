package com.simple;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

public class Faculty {


	private List<String> facultyMembers;

	private String facultyName;


	@PostConstruct
	public void initialize() {
		this.facultyMembers = new ArrayList<String>();
		facultyMembers.add("Gautham Anand");
		facultyMembers.add("Nandana Anand");
		facultyMembers.add("Lakshmi Anand");
		facultyName = "Computer enginerring";
	}

	public List<String> getFacultyMembers() {
		return facultyMembers;
	}

	public String getFacultyName() {
		return facultyName;
	}

}
