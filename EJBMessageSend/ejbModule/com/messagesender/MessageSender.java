package com.messagesender;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
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
@Singleton
@LocalBean
public class MessageSender {


	Map<String,String> userNameMap;

	public MessageSender() {
		userNameMap = new HashMap<String,String>();
	}

	public void addName(String key,String value) {
		userNameMap.put(key, value);
	}

	public Map<String,String> getNamesMap() {
		return userNameMap;
	}


	public void postMessage() {
		Context context;
		try {
			context = getInitialContext();
			Queue queue = (Queue) context.lookup("jms/queue/NameQueue");
			QueueConnectionFactory factory
			= (QueueConnectionFactory) context.lookup("ConnectionFactory");

			QueueConnection connection = factory.createQueueConnection();
			QueueSession session
			= connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(queue);
			ObjectMessage objectMessage
			= session.createObjectMessage();
			objectMessage.setObject((Serializable) getNamesMap());
			sender.send(objectMessage);

		} catch (NamingException ex) {
			Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
		} catch (JMSException ex) {
			Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public static Context getInitialContext()
			throws javax.naming.NamingException {

		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080/");
		//This property is important for remote resolving
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		//This property is not important for remote resolving
		jndiProperties.put("org.jboss.ejb.client.scoped.context", "true");
		return new javax.naming.InitialContext(jndiProperties);
	}

}
