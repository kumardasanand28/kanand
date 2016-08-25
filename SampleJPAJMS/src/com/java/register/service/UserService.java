package com.java.register.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.java.register.bean.Address;
import com.java.register.bean.User;
import com.java.register.jpabean.AddressDetailsJPA;
import com.java.register.jpabean.AddressJPABean;
import com.java.register.jpabean.ProjectJPA;
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
		List<UserJPABean> userList = fetchUserJPABean(query);
		List<User> list = copyToUserBean(userList);
		return list;
	}

	private List<UserJPABean> fetchUserJPABean(String query) {
		List<UserJPABean> userList = new ArrayList<UserJPABean>();
		EntityManager entitymanager = createEntityManager();

		Query jpaQuery = entitymanager.createQuery(query,UserJPABean.class);
		userList = jpaQuery.getResultList();
		return userList;
	}


	public void addAddress(User user) throws Exception{
		EntityManager em = createEntityManager();
		UserJPABean jpaBean = em.find(UserJPABean.class, user.getId());

		AddressDetailsJPA details = new AddressDetailsJPA();
		AddressJPABean address = new AddressJPABean();
		address.setAddressNickName(user.getAddressList().get(0).getAddressNickName());

		details.setStreet(user.getAddressList().get(0).getStreet());
		details.setCity(user.getAddressList().get(0).getCity());
		details.setState(user.getAddressList().get(0).getState());
		details.setZip(user.getAddressList().get(0).getZip());

		em.getTransaction().begin();
		//em.persist(jpaBean);

		details.setAddress(address);
		em.persist(details);

		address.setUser(jpaBean);
		em.persist(address);


		em.getTransaction().commit();
		em.close();

	}

	private EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();
		return em;
	}

	public void updateUser(User user) throws Exception {
		EntityManager em = createEntityManager();
		UserJPABean jpaBean = em.find(UserJPABean.class, user.getId());


		em.getTransaction().begin();
		jpaBean.setFullName(user.getFullName());
		jpaBean.setAge(user.getAge());
		jpaBean.setGender(user.getGender());
		jpaBean.setPassedYear(user.getYearPassed());
		jpaBean.setQualification(user.getQualification());

		em.persist(jpaBean);
		em.getTransaction().commit();
		em.close();

	}


	public void insertIntoDataBase(User user) throws Exception {
		persistUserData(user);
	}

	private void persistUserData(User user) {

		UserJPABean userBean = new UserJPABean();
		AddressDetailsJPA details = new AddressDetailsJPA();
		AddressJPABean address = new AddressJPABean();

		//user details
		userBean.setFullName(user.getFullName());
		userBean.setAge(user.getAge());
		userBean.setGender(user.getGender());
		userBean.setPassedYear(user.getYearPassed());
		userBean.setQualification(user.getQualification());
		
		//address details
		address.setAddressNickName(user.getAddressList().get(0).getAddressNickName());
		details.setStreet(user.getAddressList().get(0).getStreet());
		details.setCity(user.getAddressList().get(0).getCity());
		details.setState(user.getAddressList().get(0).getState());
		details.setZip(user.getAddressList().get(0).getZip());

		
		
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		
		List<String> projectList = user.getProjectName();
		Set<ProjectJPA> proJpaList = new HashSet<ProjectJPA>();
		ProjectJPA pJpa = new ProjectJPA();
		for(String project : projectList){
			
			pJpa.setProjectName(project);
			proJpaList.add(pJpa);
			em.persist(pJpa);
			//em.clear();
		}
		userBean.setProject(proJpaList);
		em.persist(userBean);
		em.persist(pJpa);
		
		details.setAddress(address);
		em.persist(details);

		address.setUser(userBean);
		em.persist(address);

		
		
		em.getTransaction().commit();
		em.close();

	}
	
	
	
	
	
	
	
	
	
	
	
	private List<User> copyToUserBean(List<UserJPABean> userList) {
		List<User> list = new ArrayList<User>();
		User user = null;
		Address address = null;
		for(UserJPABean bean : userList){
			user = new User();
			user.setId(bean.getId());
			user.setFullName(bean.getFullName());
			user.setAge(bean.getAge());
			user.setGender(bean.getGender());
			user.setQualification(bean.getQualification());
			user.setYearPassed(bean.getPassedYear());
			List<AddressJPABean> addressList = (List<AddressJPABean>) bean.getAddressList();
			List<Address> addressUserList = new ArrayList<Address>();
			for(AddressJPABean addressJpa : addressList){
				address = new Address();
				address.setAddressNickName(addressJpa.getAddressNickName());
				address.setAddressId(addressJpa.getAddressId());
				AddressDetailsJPA details = addressJpa.getAddressDetails();
				address.setAddressDetailsid(details.getAddressDetailsid());
				address.setCity(details.getCity());
				address.setState(details.getState());
				address.setZip(details.getZip());
				address.setStreet(details.getStreet());
				addressUserList.add(address);
			}
			user.setAddressList(addressUserList);
			user.setAddressAsText(user.getAddressAsText());
			list.add(user);
		}
		return list;
	}

}
