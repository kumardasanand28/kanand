package com.java.register.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.java.register.bean.User;
import com.java.register.jpabean.AddressDetailsJPA;
import com.java.register.jpabean.AddressJPABean;
import com.java.register.jpabean.UserJPABean;



public class UserService {

	public List<User> fetchUsers() throws Exception {

		String query = "SELECT u FROM UserJPABean u";
		List<User> userList = fetchUser(query);

		return userList;

	}

	public User fetchUser(int id) throws Exception {

		String query = "SELECT u FROM UserJPABean u where u.id=" + id;
		List<User> userList = fetchUser(query);

		return userList.get(0);

	}

	@SuppressWarnings("unchecked")
	private List<User> fetchUser(String query) throws Exception {
		List<UserJPABean> userList = new ArrayList<UserJPABean>();
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("persistenceUnit" );
		EntityManager entitymanager = emfactory.createEntityManager();

		Query jpaQuery = entitymanager.createQuery(query,UserJPABean.class);
		userList = jpaQuery.getResultList();
		List<User> list = copyToUserBean(userList);
		return list;
	}

	private List<User> copyToUserBean(List<UserJPABean> userList) {
		List<User> list = new ArrayList<User>();
		User user = null;
		for(UserJPABean bean : userList){
			user = new User();
			user.setId(bean.getId());
			user.setFullName(bean.getFullName());
			user.setAge(bean.getAge());
			user.setGender(bean.getGender());
			list.add(user);
		}
		return list;
	}

	public void updateUser(User user) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();
		UserJPABean jpaBean = em.find(UserJPABean.class, user.getId());


		em.getTransaction().begin();
		jpaBean.setFullName(user.getFullName());
		jpaBean.setAge(user.getAge());
		jpaBean.setGender(user.getGender());
		em.persist(jpaBean);
		em.getTransaction().commit();
		em.close();

	}


	/**
	 * @param user
	 */
	private void persistUserData(User user) {
		UserJPABean jpaBean = new UserJPABean();
		jpaBean.setFullName(user.getFullName());
		jpaBean.setAge(user.getAge());
		jpaBean.setGender(user.getGender());


		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(jpaBean);
		em.getTransaction().commit();
		em.close();
	}

	public void insertIntoDataBase(User user) throws Exception {
		//persistUserData(user);

		persistSampleData(user);

	}

	private void persistSampleData(User user) {
		AddressDetailsJPA details = new AddressDetailsJPA();
		AddressJPABean address = new AddressJPABean();
		address.setAddressNickName(user.getAddressNickName());
		details.setStreet(user.getStreet());
		details.setCity(user.getCity());
		details.setState(user.getState());
		details.setZip(user.getZip());
		details.setAddress(address);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(details);
		em.getTransaction().commit();
		em.close();

	}

	public String getInterestList(String[] interests) {
		String list = Arrays.toString(interests);
		return list.substring(1, list.length() - 1);
	}


}
