package com.stateful;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import com.entity.UserEntity;

/**
 * Session Bean implementation class UserStateFul
 */
@Stateful
@LocalBean
public class UserStateFul {

	/**
	 * Default constructor. 
	 */
	public UserStateFul() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "user-name", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	public void add(UserEntity userEntity) throws Exception {
		entityManager.persist(userEntity);
	}

	public void delete(UserEntity userEntity) throws Exception {
		entityManager.remove(userEntity);
	}

	public List<UserEntity> getUsers() throws Exception {
		Query query = entityManager.createQuery("SELECT m from UserEntity as m");
		return query.getResultList();
	}

}
