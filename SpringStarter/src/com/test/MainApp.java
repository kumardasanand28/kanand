package com.test;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.student.bean.Student;
import com.student.dao.impl.StudentJDBCTemplate;

public class MainApp {
	public static void main(String[] args) {
		AbstractApplicationContext  context =  new ClassPathXmlApplicationContext("Beans.xml");
		/*
		TextEditor objA = (TextEditor) context.getBean("textEditor");
		objA.spellCheck();
		objA.getAddressList();
		objA.getAddressMap();
		objA.getAddressSet();
		objA.getAddressProp();
		 */


		StudentJDBCTemplate studentJDBCTemplate =  (StudentJDBCTemplate)context.getBean("studentJDBCTemplate");

		studentJDBCTemplate.create("Anand1", 1);
		studentJDBCTemplate.create("Gautham1", 2);
		studentJDBCTemplate.create("Appu1", 3);


		System.out.println("------Listing Multiple Records--------" );
		List<Student> students = studentJDBCTemplate.listStudents();
		for (Student record : students) {
			System.out.print("ID : " + record.getId() );
			System.out.print(", Name : " + record.getName() );
			System.out.println(", Age : " + record.getAge());
		}

	}

}