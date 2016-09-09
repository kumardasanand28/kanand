package com.messagereciever;

import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.entity.UserEntity;
import com.stateful.UserStateFul;
import com.stateful.UserStateFullBean;

/**
 * Message-Driven Bean implementation class for: MessageRecieve
 */
@MessageDriven(mappedName = "NameQueue",name="Name Queue" ,description="Message Listener for recieving the registration details", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",propertyValue = "${NAME_QUEUE_NAME}")
})
public class MessageRecieve implements MessageListener {


	@Resource
	private MessageDrivenContext mdctx;
	
	@EJB
	private UserStateFul user;
	
	@EJB
	private UserStateFullBean userBean;

	/**
	 * Default constructor. 
	 */
	public MessageRecieve() {
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
