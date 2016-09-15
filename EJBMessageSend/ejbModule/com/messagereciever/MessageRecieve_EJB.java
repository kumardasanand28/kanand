package com.messagereciever;

import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.entity.UserEntity;
import com.stateful.UserStateFul;

public class MessageRecieve_EJB {


	@Resource
	private MessageDrivenContext mdctx;
	
	@EJB
	private UserStateFul user;
	

	/**
	 * Default constructor. 
	 */
	public MessageRecieve_EJB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		System.out.println("Message driven Bean is working!!!");
		ObjectMessage objectMessage = null;
		try {
			objectMessage = (ObjectMessage) message;
			Map<String,String> namesMap = (Map) objectMessage.getObject();
			try {
				user.add(new UserEntity(namesMap.get("name"), namesMap.get("empId")));
				//userBean.add(new UserEntity(namesMap.get("name"), namesMap.get("empId")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(namesMap);
			
			
		} catch (JMSException ex) {
			ex.printStackTrace();
			mdctx.setRollbackOnly();
		}
	}

}
