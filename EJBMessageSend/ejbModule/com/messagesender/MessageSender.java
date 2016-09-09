package com.messagesender;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.NamingException;


/**
 * Session Bean implementation class MessageSender
 */
@Stateless
public class MessageSender {

	private Context ctx = null;

	public void postMessage(String name, String empId) throws Exception {
		Map<String,String> userNameMap =  new HashMap<String,String>();

		System.out.println("Context : "+ctx);
		userNameMap.put("name", name);
		userNameMap.put("empId", empId);

		Context context;
		QueueConnection connection = null;
		try {
			context = getInitialContext();
			Queue queue = (Queue) context.lookup("jms/queue/NameQueue");
			QueueConnectionFactory factory
			= (QueueConnectionFactory) context.lookup("ConnectionFactory");

			connection = factory.createQueueConnection();
			QueueSession session
			= connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(queue);
			ObjectMessage objectMessage
			= session.createObjectMessage();
			objectMessage.setObject((Serializable)userNameMap);
			sender.send(objectMessage);
			System.out.println("POSTING FROM A @Stateless BEAN");

		} catch (NamingException ex) {
			Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
			throw ex;
		} catch (JMSException ex) {
			Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
			throw ex;
		}catch (Exception e) {
			Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, e);
			throw e;
		}finally {
			if(connection != null){
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	private Context getInitialContext()
			throws javax.naming.NamingException {
		if(ctx == null){
			Properties jndiProperties = new Properties();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080/");
			//This property is important for remote resolving
			jndiProperties.put("jboss.naming.client.ejb.context", true);
			ctx = new javax.naming.InitialContext(jndiProperties);
		}
		return ctx;
	}


	public void setInititalContext(Context context){
		this.ctx = context;
	}

}
