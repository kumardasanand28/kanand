package com.spring.bean;

import com.spring.jdbc.SpringJDBCTest;

public class SpringBean {

	private String name;

	private SpringJDBCTest jdbcTest;

	//testing injection
	public void testInjection() throws Exception{
		jdbcTest.testJDBC();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpringJDBCTest getJdbcTest() {
		return jdbcTest;
	}

	public void setJdbcTest(SpringJDBCTest jdbcTest) {
		this.jdbcTest = jdbcTest;
	}

}
