/**
 * 	TransactionDefinition.PROPAGATION_SUPPORTS
		Support a current transaction; execute non-transactionally if none exists.

		TransactionDefinition.PROPAGATION_MANDATORY
		Support a current transaction; throw an exception if no current transaction exists.

		TransactionDefinition.PROPAGATION_NESTED
		Execute within a nested transaction if a current transaction exists.

		TransactionDefinition.PROPAGATION_NEVER
		Do not support a current transaction; throw an exception if a current transaction exists.

		TransactionDefinition.PROPAGATION_NOT_SUPPORTED
		Do not support a current transaction; rather always execute non-transactionally.


		TransactionDefinition.PROPAGATION_REQUIRED
		Support a current transaction; create a new one if none exists.

		TransactionDefinition.PROPAGATION_REQUIRES_NEW
		Create a new transaction, suspending the current transaction if one exists.
 */

package com.spring.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class SpringJDBCTest {

	private JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager transactionManager;

	private static final String EMP_INSERT = "insert into employee(EMP_NAME,SALARY) values(?,?)"; 
	private static final String EMP_UPDATE = "update employee set salary = ? where EMP_NAME = ?";

	private static final String PROJECT_INSERT = "insert into project1(PROJECT_NAME,EMP_NAME) values(?,?)";

	private static final String EMP_PROJECT_SELECT = "select * from employee e, project p where e.EMP_NAME = p.EMP_NAME AND e.EMP_NAME = ?";
	private static final String EMP_PROJECT_SELECT1 = "select * from employee e, project p where e.EMP_NAME = p.EMP_NAME";


	//DriverManagerDataSource class used to configure datasource
	//set this datasource to jdbctemplate
	public boolean testJDBC() throws Exception{

		TransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);


		System.out.println(def.getIsolationLevel());
		System.out.println(def.getPropagationBehavior());
		System.out.println(def.getName());
		System.out.println(def.getTimeout());
		System.out.println(def.isReadOnly());



		try{
			insertEmp("Appu", 200);
			//update(300,1);
			insertProject("WAG", "Appu");
			Employee emp = fetchAllrecords("Appu");
			update(300,"Appu");

			transactionManager.commit(status);

		}catch (Exception e) {
			System.out.println(e);
			transactionManager.rollback(status);
		}


		System.out.println("Successful!!!");
		return true;
	}

	private Employee fetchAllrecords(String name) {
		Employee emp = (Employee) jdbcTemplate.queryForObject(EMP_PROJECT_SELECT,new Object[] {name},new EmployeeRowMapper());
		//List<Employee> empList = jdbcTemplate.query(EMP_PROJECT_SELECT1, new BeanPropertyRowMapper(Employee.class));
		return emp;

	}

	public Boolean insertEmp(String name, int salary){
		return jdbcTemplate.execute(EMP_INSERT, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, name);
				ps.setInt(2, salary);
				return ps.execute();

			}
		});
	}


	public Boolean insertProject(String projectName, String empName){
		return jdbcTemplate.execute(PROJECT_INSERT, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, projectName);
				ps.setString(2, empName);
				return ps.execute();

			}
		});
	}



	public Boolean update(int salary,String name){
		return jdbcTemplate.execute(EMP_UPDATE, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, salary);
				ps.setString(2, name);
				return ps.execute();

			}
		});
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
