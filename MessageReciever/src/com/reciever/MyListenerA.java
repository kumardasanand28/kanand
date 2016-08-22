package com.reciever;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import message.EventMessage;

public class MyListenerA implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				Object object = ((ObjectMessage) message).getObject();
				EventMessage event =(EventMessage) object;
				
				List<String> eventList = event.getMessageText();
				System.out.println("The Message Recieved is...");
				for(String name : eventList){
					System.out.println(name);
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}

}
