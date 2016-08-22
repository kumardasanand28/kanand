package com.reciever;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageReciever {

	public static void main(String[] args) {
		InitialContext ctx;
		try {
			ctx = new InitialContext();

			QueueConnectionFactory f=(QueueConnectionFactory)ctx.lookup("jms/myConnectionFactory");  
			QueueConnection con=f.createQueueConnection();  
			con.start();  

			QueueSession ses=con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);  

			Queue t=(Queue)ctx.lookup("jms/EmployeeManagementQueue");

			QueueReceiver receiver=ses.createReceiver(t);  
			MyListenerA listener=new MyListenerA();  
			receiver.setMessageListener(listener);  

			System.out.println("Receiver1 is ready, waiting for messages...");  
			while(true){                  
				Thread.sleep(1000);
			}  

		} 
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
