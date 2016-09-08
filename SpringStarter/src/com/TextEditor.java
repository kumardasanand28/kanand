package com;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TextEditor {

	private SpellChecker spellChecker;

	List addressList;
	Set  addressSet;
	Map  addressMap;
	Properties addressProp;

	public SpellChecker getSpellChecker() {
		return spellChecker;
	}

	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("Inside setSpellChecker." +spellChecker);
		this.spellChecker = spellChecker;
	}

	public void spellCheck() {
		spellChecker.checkSpelling();
	}

	public List getAddressList() {
		System.out.println("List Elements :"  + addressList);
		return addressList;
	}

	public void setAddressList(List addressList) {
		this.addressList = addressList;
	}

	public Set getAddressSet() {
		System.out.println("Set Elements :"  + addressSet);
		return addressSet;
	}

	public void setAddressSet(Set addressSet) {
		this.addressSet = addressSet;
	}

	public Map getAddressMap() {
		System.out.println("Map Elements :"  + addressMap);
		return addressMap;
	}

	public void setAddressMap(Map addressMap) {
		this.addressMap = addressMap;
	}

	public Properties getAddressProp() {
		return addressProp;
	}

	public void setAddressProp(Properties addressProp) {
		System.out.println("Property Elements :"  + addressProp);
		this.addressProp = addressProp;
	}

}
