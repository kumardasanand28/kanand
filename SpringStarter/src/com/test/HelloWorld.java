package com.test;

public class HelloWorld {

	public void init() {
		System.out.println("INITALIZED !! "+ this.hashCode());
	}

	public void destroy(){
		System.out.println("Bean will destroy now.");
	}

	private String message;

	public void setMessage(String message){
		this.message  = message;
	}

	public void getMessage(){
		System.out.println("Your Message : " + message);
	}

	private String message1;

	public void getMessage1() {
		System.out.println("Your Message1 : " + message1);
	}

	public void setMessage1(String message1) {
		this.message1 = message1;
	}
}