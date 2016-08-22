package subsriber;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.reciever.MyListenerA;

public class MessageSubsriberA {

	public static void main(String[] args) {
		try {
			
			InitialContext ctx=new InitialContext();  
            TopicConnectionFactory f=(TopicConnectionFactory)ctx.lookup("jms/myConnectionFactory");  
            TopicConnection con=f.createTopicConnection();  
            con.start();  
            
            TopicSession ses=con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);  
            Topic t=(Topic)ctx.lookup("jms/EmployeeManagementTopic");  
            TopicSubscriber receiver=ses.createSubscriber(t);  
            
            MyListenerA listener=new MyListenerA();  
            receiver.setMessageListener(listener);  
            
            System.out.println("SubscriberA is ready, waiting for messages...");  
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
