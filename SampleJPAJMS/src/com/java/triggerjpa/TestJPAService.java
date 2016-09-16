package com.java.triggerjpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.java.register.constants.Constants;
import com.java.register.testjpa.AddressJPABean;
import com.java.register.testjpa.PartyJPABean;
import com.java.register.testjpa.PolicyJPABean;
import com.java.register.testjpa.VehicleJPABean;

public class TestJPAService {

	private String random = "_One";
	private String random1 = "_Two";


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
			Collection<PartyJPABean> listParty = new ArrayList<PartyJPABean>();
			listParty.add(party);
			listParty.add(party1);
			policy.setPartyList(listParty);





		





			VehicleJPABean vehicle = new VehicleJPABean();
			vehicle.setVehicleName(vehicleMap.get(Constants.VEHICLE_NAME+random).toString());
			vehicle.setPolicy(policy);
			

			VehicleJPABean vehicle1 = new VehicleJPABean();
			vehicle1.setVehicleName(vehicleMap.get(Constants.VEHICLE_NAME+random1).toString());
			vehicle1.setPolicy(policy);
			
			
			
			
			
			
			AddressJPABean address = new AddressJPABean();
			address.setCity(addressMap.get("city"+random).toString());

			AddressJPABean address1 = new AddressJPABean();
			address1.setCity(addressMap.get("city"+random).toString());
			Collection<AddressJPABean> addressList = new ArrayList<AddressJPABean>();
			addressList.add(address);
			addressList.add(address1);
			
			
			vehicle.setAddressList(addressList);
			vehicle1.setAddressList(addressList);
			
			
			Collection<VehicleJPABean> vehicleList = new ArrayList<VehicleJPABean>();
			vehicleList.add(vehicle);
			vehicleList.add(vehicle1);
			policy.setVehicleList(vehicleList);



			EntityManager em = createEntityManager();
			em.getTransaction().begin();
			em.persist(policy);
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			System.out.println(e);
		}

	}


	private EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("partyUnit");
		EntityManager em = emf.createEntityManager();
		return em;
	}

}
