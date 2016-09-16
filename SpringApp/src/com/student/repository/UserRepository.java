package com.student.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.student.entity.UserEntity;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserRepository {


	private static final String SELECT_QUERY = "select p from UserEntity p";

	@PersistenceContext(type= PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public void insert(UserEntity user) {
		entityManager.persist(user);
	}

	public List<UserEntity> selectAll() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<UserEntity> persons = (List<UserEntity>) query.getResultList();
		return persons;
	}

}
