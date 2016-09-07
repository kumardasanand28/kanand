package com.stateless;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.simple.Faculty;

@Stateless
public class Course {

	@Inject//if this need to work we need META-INF/beans.xml
	private Faculty faculty;

	private String courseName;

	private int capacity;
	
	
	@PostConstruct
    private void init() {

        this.courseName = "ATG CSC";
        this.capacity = 100;
    }
	
	public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public Faculty getFaculty() {
        return faculty;
    }
    
    
    //method to test
    
    public String testCourseStateless(){
    	List<String> faculty = this.faculty.getFacultyMembers();
		return getCourseName()+getCapacity()+faculty.get(1)+this.faculty.getFacultyName();
    }

}
