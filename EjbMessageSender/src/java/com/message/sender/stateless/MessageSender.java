/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.message.sender.stateless;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.jms.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 *
 * @author BEAST
 */
@Stateless
public class MessageSender implements MessageSenderRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    List<String> namesList;

    public MessageSender() {
        namesList = new ArrayList<String>();
    }

    public void addNames(List names) {
        namesList = names;
    }

    public List<String> getNames() {
        return namesList;
    }

    public void postMessage() {
        Context context;
        try {
            context = getInitialContext();
            Queue queue = (Queue) context.lookup("/queue/NameQueue");
            QueueConnectionFactory factory
                    = (QueueConnectionFactory) context.lookup("ConnectionFactory");

            QueueConnection connection = factory.createQueueConnection();
            QueueSession session
                    = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(queue);
            ObjectMessage objectMessage
                    = session.createObjectMessage();
            objectMessage.setObject((Serializable) getNames());
            sender.send(objectMessage);

        } catch (NamingException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Context getInitialContext()
            throws javax.naming.NamingException {

        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jnp.interfaces.NamingContextFactory");
        p.put(Context.URL_PKG_PREFIXES,
                " org.jboss.naming:org.jnp.interfaces");
        p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        return new javax.naming.InitialContext(p);
    }
}
