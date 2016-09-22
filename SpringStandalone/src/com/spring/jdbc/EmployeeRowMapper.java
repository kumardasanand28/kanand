package com.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Object>{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setEmpName(rs.getString("EMP_NAME"));
		emp.setSalary(rs.getInt("SALARY"));
		emp.setProjectName(rs.getString("PROJECT_NAME"));
		return emp;
	}

}
