package com.stateful;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import com.entity.UserEntity;
/**
 * Session Bean implementation class UserStateFul
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserStateFul {

	/**
	 * Default constructor. 
	 */
	public UserStateFul() {
		nameMap = new HashMap<String,String>();
	}

	@Resource
	private SessionContext context;
	
	private String testName;
	
	private Map<String,String> nameMap;
	
	
	@PersistenceContext(unitName = "user-name", type= PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(UserEntity userEntity) throws Exception {
		try{
			entityManager.persist(userEntity);	
		}catch (Exception e) {
			context.setRollbackOnly();
			System.out.println(e);
			throw e;
		}
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(UserEntity userEntity) throws Exception  {
		try{
			entityManager.remove(userEntity);	
		}catch (Exception e) {
			context.setRollbackOnly();
			System.out.println(e);
			throw e;
		}
		
	}

	public List<UserEntity> getUsers() throws Exception {
		Query query = entityManager.createQuery("SELECT m from UserEntity as m");
		return query.getResultList();
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Map<String, String> getNameMap() {
		return nameMap;
	}

	public void setNameMap(Map<String, String> nameMap) {
		this.nameMap = nameMap;
	}

}
