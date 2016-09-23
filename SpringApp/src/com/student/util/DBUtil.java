package com.student.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DBUtil {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	/*public void initialize() throws ClassNotFoundException{
		DataSource dataSource = getDataSource();
		try {
			Class.forName("org.h2.Driver");
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE USERS (ID INTEGER IDENTITY, FULL_NAME VARCHAR(50), "
					+ "STUDENT_HOBBY VARCHAR(100), STUDENT_MOBILE_NUMBER VARCHAR(50), STUDENT_DOB DATE, STUDENT_SKILLSET VARCHAR(150))");
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		DataSource dataSource = getDataSource();
		try{
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("DROP TABLE USERS");
			statement.close();
			connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
