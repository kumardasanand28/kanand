package main.java.com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.com.aws.AWSConnectionManager;
import main.java.com.bean.User;

public class UserService {

	public List<User> fetchUsers() throws Exception {

		String query = "SELECT * FROM USERS";
		List<User> userList = fetchUser(query);

		return userList;

	}

	public User fetchUser(int id) throws Exception {

		String query = "SELECT * FROM USERS where id=" + id;
		List<User> userList = fetchUser(query);

		return userList.get(0);

	}

	private List<User> fetchUser(String query) throws Exception {
		List<User> userList = new ArrayList<>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = getMySqlConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		User user = null;
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setFullName(rs.getString("FULL_NAME"));
			user.setAge(rs.getInt("AGE"));
			user.setQualification(rs.getString("QUALIFICATION"));
			user.setYearPassed(rs.getInt("PASSED_YEAR"));
			user.setGender(rs.getString("GENDER"));
			String interests = rs.getString("INTERESTS");
			user.setInterests(interests.split(","));
			userList.add(user);
		}
		st.close();
		return userList;
	}

	public void updateUser(User user) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = getMySqlConnection();

		String query = "update USERS set FULL_NAME =?,AGE= ?,QUALIFICATION=?,PASSED_YEAR=?, GENDER=?,INTERESTS=? where id=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, user.getFullName());
		preparedStmt.setInt(2, user.getAge());
		preparedStmt.setString(3, user.getQualification());
		preparedStmt.setInt(4, user.getYearPassed());
		preparedStmt.setString(5, user.getGender());
		preparedStmt.setString(6, getInterestList(user.getInterests()));
		preparedStmt.setInt(7, user.getId());
		// execute the preparedstatement
		preparedStmt.execute();

		conn.close();

	}

	public void createUserTable() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = getMySqlConnection();
		
		String query="CREATE TABLE USERS(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, FULL_NAME VARCHAR(20), AGE int, QUALIFICATION VARCHAR(20), PASSED_YEAR VARCHAR(6),GENDER VARCHAR(6),INTERESTS VARCHAR(100))";
		
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);
		System.out.println(rs);
	}
	public void insertIntoDataBase(User user) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = getMySqlConnection();

		String insertQuery = " insert into USERS (FULL_NAME, AGE, QUALIFICATION, PASSED_YEAR, GENDER, INTERESTS)"
				+ " values (?, ?, ?, ?, ?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(insertQuery);
		preparedStmt.setString(1, user.getFullName());
		preparedStmt.setInt(2, user.getAge());
		preparedStmt.setString(3, user.getQualification());
		preparedStmt.setInt(4, user.getYearPassed());
		preparedStmt.setString(5, user.getGender());
		preparedStmt.setString(6, getInterestList(user.getInterests()));

		// execute the preparedstatement
		preparedStmt.execute();

		conn.close();

	}

	public String getInterestList(String[] interests) {
		String list = Arrays.toString(interests);
		return list.substring(1, list.length() - 1);
	}

	public Connection getMySqlConnection() throws Exception {
		return AWSConnectionManager.getRemoteConnection();

	}

}
