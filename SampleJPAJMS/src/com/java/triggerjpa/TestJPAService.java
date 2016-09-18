package com.java.triggerjpa;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.java.register.constants.Constants;
import com.java.register.testjpa.AddressJPABean;
import com.java.register.testjpa.PartyJPABean;
import com.java.register.testjpa.PolicyJPABean;
import com.java.register.testjpa.VehicleJPABean;

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


			PartyJPABean party = new PartyJPABean();
			party.setPartyName(partyMap.get(Constants.PARTY_NAME+random).toString());

			PartyJPABean party1 = new PartyJPABean();
			party1.setPartyName(partyMap.get(Constants.PARTY_NAME+random1).toString());

			PolicyJPABean policy = new PolicyJPABean();
			policy.setPolicyName(policyMap.get(Constants.POLICY_NAME).toString());
			party.setPolicy(policy);
			party1.setPolicy(policy);


			VehicleJPABean vehicle = new VehicleJPABean();
			vehicle.setVehicleName(vehicleMap.get(Constants.VEHICLE_NAME+random).toString());
			vehicle.setPolicy(policy);
			

			VehicleJPABean vehicle1 = new VehicleJPABean();
			vehicle1.setVehicleName(vehicleMap.get(Constants.VEHICLE_NAME+random1).toString());
			vehicle1.setPolicy(policy);
			
			
			
			
			
			
			AddressJPABean address = new AddressJPABean();
			address.setCity(addressMap.get("city"+random).toString());
			address.setState("Kerala");
			address.setVehicle(vehicle);

			AddressJPABean address1 = new AddressJPABean();
			address1.setCity(addressMap.get("city"+random1).toString());
			address1.setVehicle(vehicle);
			address1.setState("Kerala");
			
			AddressJPABean address2 = new AddressJPABean();
			address2.setCity(addressMap.get("city"+random2).toString());
			address2.setVehicle(vehicle1);
			address2.setState("Kerala");
			
			AddressJPABean address3 = new AddressJPABean();
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
	private void persistDetails(PartyJPABean party, PartyJPABean party1,
			VehicleJPABean vehicle, VehicleJPABean vehicle1,
			AddressJPABean address, AddressJPABean address1,
			AddressJPABean address2, AddressJPABean address3, EntityManager em) {
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
		String query = "SELECT u FROM PolicyJPABean u where u.policyName= :policyName";
		EntityManager entitymanager = createEntityManager();
		Query jpaQuery = entitymanager.createQuery(query,PolicyJPABean.class).setParameter("policyName", policyName);
		List<PolicyJPABean> policyJPA = (List<PolicyJPABean>) jpaQuery.getResultList();
		PolicyJPABean policyJpa = policyJPA.get(0);
		
		System.out.println(policyJpa);
	}
	
	public void delete(String policyName) {
		String query = "SELECT u FROM PolicyJPABean u where u.policyName= :policyName";
		EntityManager entitymanager = createEntityManager();
		Query jpaQuery = entitymanager.createQuery(query,PolicyJPABean.class).setParameter("policyName", policyName);
		List<PolicyJPABean> policyJPA = (List<PolicyJPABean>) jpaQuery.getResultList();
		PolicyJPABean policyJpa = policyJPA.get(0);
		
		try{
			entitymanager.getTransaction().begin();
			//entitymanager.remove(policyJpa);
			List<PartyJPABean> partyList = (List<PartyJPABean>) policyJpa.getPartyList();
			policyJpa.removePartyChild(partyList.get(0));
			entitymanager.getTransaction().commit();
			entitymanager.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
