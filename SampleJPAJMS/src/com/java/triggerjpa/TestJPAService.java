package com.java.triggerjpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.java.register.constants.Constants;
import com.java.register.mantomanyjpa.EmpProjectAssociation;
import com.java.register.mantomanyjpa.Employee;
import com.java.register.mantomanyjpa.Project;
import com.java.register.projectionquery.PolicyParty;
import com.java.register.testjpa.Address;
import com.java.register.testjpa.Party;
import com.java.register.testjpa.Policy;
import com.java.register.testjpa.Vehicle;
import com.java.register.testjpa.immutable.IAddress;
import com.java.register.testjpa.immutable.IPolicy;
import com.java.register.testjpa.immutable.IVehicle;
import com.java.register.testjpa.manytomany.MPolicy;

public class TestJPAService {

	private String random = "_One";
	private String random1 = "_Two";
	private String random2 = "_Three";
	private String random3 = "_Four";


	public void persistSampleData(Map<String, Object> policyMap){
		try{

			Map<String, Object> partyMap = (Map<String, Object>) policyMap.get(Constants.PARTY_POLICY);
			Map<String, Object> vehicleMap = (Map<String, Object>) policyMap.get(Constants.VEHICLE_POLICY);
			Map<String, Object> addressMap = (Map<String, Object>) vehicleMap.get(Constants.ADDRESS_VEHICLE);


			Party party = new Party();
			party.setPartyName(partyMap.get(Constants.PARTY_NAME+random).toString());

			Party party1 = new Party();
			party1.setPartyName(partyMap.get(Constants.PARTY_NAME+random1).toString());

			Policy policy = new Policy();
			policy.setPolicyName(policyMap.get(Constants.POLICY_NAME).toString());
			party.setPolicy(policy);
			party1.setPolicy(policy);


			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleName(vehicleMap.get(Constants.VEHICLE_NAME+random).toString());
			vehicle.setPolicy(policy);


			Vehicle vehicle1 = new Vehicle();
			vehicle1.setVehicleName(vehicleMap.get(Constants.VEHICLE_NAME+random1).toString());
			vehicle1.setPolicy(policy);






			Address address = new Address();
			address.setCity(addressMap.get("city"+random).toString());
			address.setState("Kerala");
			address.setVehicle(vehicle);

			Address address1 = new Address();
			address1.setCity(addressMap.get("city"+random1).toString());
			address1.setVehicle(vehicle);
			address1.setState("Kerala");

			Address address2 = new Address();
			address2.setCity(addressMap.get("city"+random2).toString());
			address2.setVehicle(vehicle1);
			address2.setState("Kerala");

			Address address3 = new Address();
			address3.setCity(addressMap.get("city"+random3).toString());
			address3.setVehicle(vehicle1);
			address3.setState("Kerala");



			EntityManager em = createEntityManager();
			em.getTransaction().begin();
			persistDetails(party, party1, vehicle, vehicle1, address, address1,
					address2, address3, em);
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			System.out.println(e);
		}

	}




	/**
	 * @param party
	 * @param party1
	 * @param vehicle
	 * @param vehicle1
	 * @param address
	 * @param address1
	 * @param address2
	 * @param address3
	 * @param em
	 */
	private void persistDetails(Party party, Party party1,
			Vehicle vehicle, Vehicle vehicle1,
			Address address, Address address1,
			Address address2, Address address3, EntityManager em) {
		em.persist(address);
		em.persist(address1);
		em.persist(address2);
		em.persist(address3);
		em.persist(vehicle);
		em.persist(vehicle1);
		em.persist(party);
		em.persist(party1);
	}




	private EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("partyUnit");
		EntityManager em = emf.createEntityManager();
		return em;
	}




	public void fetchTest(String policyName) {
		String query = "SELECT u FROM Policy u where u.name= :policyName";
		EntityManager entitymanager = createEntityManager();
		Query jpaQuery = entitymanager.createQuery(query,Policy.class).setParameter("policyName", policyName);
		List<Policy> policyJPA = (List<Policy>) jpaQuery.getResultList();
		Policy policyJpa = policyJPA.get(0);

		System.out.println(policyJpa);
		fetchTestManyToMany("testpolicyname1_Two");
		List<Party> partyList = (List<Party>) policyJpa.getPartyList();
		fetchTestManyToOne(partyList.get(0).getPartyName());
	}
	
	public void fetchTestManyToMany(String policyName) {
		String query = "SELECT u FROM MPolicy u where u.name= :policyName";
		EntityManager entitymanager = createEntityManager();
		Query jpaQuery = entitymanager.createQuery(query,Policy.class).setParameter("policyName", policyName);
		List<MPolicy> policyJPA = (List<MPolicy>) jpaQuery.getResultList();
		MPolicy policyJpa = policyJPA.get(0);

		System.out.println(policyJpa);
	}
	
	public void fetchTestManyToOne(String partyName) {
		try{
		String query = "SELECT u FROM Party u where u.name= :partyName";
		EntityManager entitymanager = createEntityManager();
		Query jpaQuery = entitymanager.createQuery(query,Party.class).setParameter("partyName", partyName);
		List<Party> vehicleJpa = (List<Party>) jpaQuery.getResultList();
		Party vehicle = vehicleJpa.get(0);
		System.out.println(vehicle);
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public void delete(String policyName) {
		String query = "SELECT u FROM Policy u where u.name= :policyName";
		EntityManager entitymanager = createEntityManager();
		Query jpaQuery = entitymanager.createQuery(query,Policy.class).setParameter("policyName", policyName);
		List<Policy> policyJPA = (List<Policy>) jpaQuery.getResultList();
		Policy policyJpa = policyJPA.get(0);
		try{
			entitymanager.getTransaction().begin();
			List<Party> partyList = (List<Party>) policyJpa.getPartyList();
			entitymanager.remove(partyList.get(0));
			/*List<Party> partyList = (List<Party>) policyJpa.getPartyList();
			policyJpa.removePartyChild(partyList.get(0));*/
			entitymanager.getTransaction().commit();
			entitymanager.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public void loadImmutableEntity(String policyName){
		IPolicy policyJpa = fetchPolicyProjectedJPA(policyName);
		List<IVehicle> vehicleList = (List<IVehicle>) policyJpa.getVehicleList();
		for(IVehicle vehicleJPAproj : vehicleList){
			List<IAddress> addressList = (List<IAddress>) vehicleJPAproj.getAddressList();
			for(IAddress address : addressList){
				System.out.println(address.getCity());
				System.out.println(address.getState());
			}
		}

	}




	private IPolicy fetchPolicyProjectedJPA(String policyName) {
		IPolicy policyJpa = null;
		try{
			String query = "SELECT u FROM IPolicy u where u.name= :policyName";
			EntityManager entitymanager = createEntityManager();
			Query jpaQuery = entitymanager.createQuery(query,IPolicy.class).setParameter("policyName", policyName);
			List<IPolicy> policyJPA = (List<IPolicy>) jpaQuery.getResultList();
			policyJpa = policyJPA.get(0);
		}catch(Exception e) {
			System.out.println(e);
		}
		return policyJpa;
	}




	public void removeEmmutableEntityCheck(String policyName){
		try{

			IPolicy policyJpa = fetchPolicyProjectedJPA(policyName);
			List<IVehicle> vehicleList = (List<IVehicle>) policyJpa.getVehicleList();
			EntityManager entitymanager = createEntityManager();

			entitymanager.getTransaction().begin();
			//removing the child entity from the parent of an immutable 
			//object does not throw error, it is silently suppressed by JPA
			policyJpa.removeVehicleChild(vehicleList.get(0));
			entitymanager.getTransaction().commit();
			entitymanager.close();


		}catch(Exception e) {
			System.out.println(e);
		}
	}


	public void testProjectionQuery(String policyName){
		try{

			EntityManager entitymanager = createEntityManager();


			String queryStr = "SELECT NEW com.java.register.projectionquery.PolicyParty(policy.name,party.name) FROM "
					+ "Policy AS policy,Party AS party WHERE policy.id=party.policy.id "
					+ "AND policy.name= :policyName";

			TypedQuery<PolicyParty> query = entitymanager.createQuery(queryStr,PolicyParty.class).
					setParameter("policyName", policyName);
			List<PolicyParty> results = query.getResultList();



			System.out.println(results);
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

	public void testManyToMany(Map<String, Object> policyMap, Map<String, Object> partyMap){
		try{
			com.java.register.testjpa.manytomany.MParty party = new com.java.register.testjpa.manytomany.MParty();
			party.setName(partyMap.get(Constants.PARTY_NAME+random).toString());

			com.java.register.testjpa.manytomany.MParty party1 = new com.java.register.testjpa.manytomany.MParty();
			party1.setName(partyMap.get(Constants.PARTY_NAME+random1).toString());


			com.java.register.testjpa.manytomany.MPolicy policy = new com.java.register.testjpa.manytomany.MPolicy();
			policy.setName(policyMap.get(Constants.POLICY_NAME).toString());

			com.java.register.testjpa.manytomany.MPolicy policy1 = new com.java.register.testjpa.manytomany.MPolicy();
			policy1.setName(policyMap.get(Constants.POLICY_NAME+random).toString());

			party.getPolicies().add(policy);
			party.getPolicies().add(policy1);

			party1.getPolicies().add(policy);
			party1.getPolicies().add(policy1);

			EntityManager entitymanager = createEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(party);
			entitymanager.persist(party1);
			entitymanager.persist(policy);
			entitymanager.persist(policy1);
			entitymanager.getTransaction().commit();
			entitymanager.close();
		}catch(Exception e) {
			System.out.println(e);
		}

	}


	public void testManyToManyExtra(Map<String, Object> projectMap, Map<String, Object> empMap){

		try{
			Project project = new Project(projectMap.get(Constants.PROJECT_NAME).toString());
			Project project1 = new Project(projectMap.get(Constants.PROJECT_NAME+random).toString());

			Employee employee = new Employee(empMap.get(Constants.EMP_NAME).toString());
			Employee employee1 = new Employee(empMap.get(Constants.EMP_NAME+random).toString());

			EmpProjectAssociation association = project.addEmployee(employee);
			EmpProjectAssociation association1 =  project.addEmployee(employee1);
			EmpProjectAssociation association2 = project1.addEmployee(employee);
			EmpProjectAssociation association3 = project1.addEmployee(employee1);
			EntityManager entitymanager = createEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(project);
			entitymanager.persist(project1);
			entitymanager.persist(employee);
			entitymanager.persist(employee1);
			entitymanager.persist(association);
			entitymanager.persist(association1);
			entitymanager.persist(association2);
			entitymanager.persist(association3);
			entitymanager.getTransaction().commit();
			entitymanager.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
