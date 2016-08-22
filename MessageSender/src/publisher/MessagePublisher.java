package publisher;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import message.EventMessage;

public class MessagePublisher {

	public static void main(String[] args) {


		try {
			InitialContext ctx = new InitialContext();


			TopicConnectionFactory f=(TopicConnectionFactory)ctx.lookup("jms/myConnectionFactory");  
			TopicConnection con=f.createTopicConnection();  
			TopicSession ses=con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);  

			con.start();  
			Topic t=(Topic)ctx.lookup("jms/EmployeeManagementTopic");  
			TopicPublisher publisher=ses.createPublisher(t);
			List<String> list = new ArrayList<>();
			list.add("Gautham");
			list.add("Anand");

			EventMessage eventMessage = new EventMessage(1,list);
			ObjectMessage objectMessage = ses.createObjectMessage();
			objectMessage.setObject(eventMessage);
			publisher.publish(objectMessage);
			System.out.println("Message successfully sent.");  


		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
