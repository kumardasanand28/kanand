import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import message.EventMessage;

public class MessageSender {

	public static void main(String[] args) {

		Connection con = null;
		try {
			System.out.println("Started");
			Context ctx = new InitialContext();

			Queue queue = (Queue)ctx.lookup("jms/EmployeeManagementQueue");
			ConnectionFactory cf = (ConnectionFactory)ctx.lookup("jms/myConnectionFactory");

			con = cf.createConnection();
			Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer msgProd = session.createProducer(queue);

			List<String> list = new ArrayList<>();
			list.add("Gautham");
			list.add("Anand");
			
			EventMessage eventMessage = new EventMessage(1,list);
			ObjectMessage objectMessage = session.createObjectMessage();
			objectMessage.setObject(eventMessage);
			msgProd.send(objectMessage);
			
		/*	TextMessage message = session.createTextMessage("Teh time is now "+new Date());
			msgProd.send(message);*/



		} catch (NamingException e) {
			System.out.println(e);
		}catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {
				if(con != null){
					con.close();
				}
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
