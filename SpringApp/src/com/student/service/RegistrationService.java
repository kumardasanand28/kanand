package com.student.service;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;


public class RegistrationService {

	private JmsTemplate jmsTemplate;
	private Queue testQueue;

	public boolean sendMessageAsJSON(String text){
		try{
			jmsTemplate.convertAndSend(testQueue,text);
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}


	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Queue getTestQueue() {
		return testQueue;
	}

	public void setTestQueue(Queue testQueue) {
		this.testQueue = testQueue;
	}

}
