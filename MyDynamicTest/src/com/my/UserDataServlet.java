package com.my;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.UserEntity;
import com.google.gson.Gson;
import com.messagesender.MessageSender;
import com.stateful.UserStateFul;


/**
 * Servlet implementation class UserDataServlet
 */
@WebServlet("/UserDataServlet")
public class UserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @EJB
    MessageSender messageSender;
    
    @EJB
    UserStateFul userStateful;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//postMessage(response);
		
		List<UserEntity> userList = null;
		try {
			userList = userStateful.getUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String json = new Gson().toJson(userList);

		System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void postMessage(HttpServletResponse response) throws IOException {
	
		QueueConnection connection = null;
		
		try {
			Context context = getInitialContext();
			Queue queue = (Queue) context.lookup("jms/queue/NameQueue");
			QueueConnectionFactory factory
			= (QueueConnectionFactory) context.lookup("ConnectionFactory");

			connection = factory.createQueueConnection();
			QueueSession session
			= connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(queue);
			ObjectMessage objectMessage = session.createObjectMessage();
			objectMessage.setObject((Serializable) messageSender.getNamesMap());
			sender.send(objectMessage);
			response.getWriter().write("Registration Success");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("Registration Failed");
		}catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("Registration Failed");
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

		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080/");
		//This property is important for remote resolving
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		//This property is not important for remote resolving
		jndiProperties.put("org.jboss.ejb.client.scoped.context", "true");
		return new javax.naming.InitialContext(jndiProperties);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		messageSender.addName("name", request.getParameter("fullname"));
		messageSender.addName("empId", request.getParameter("empid"));
		postMessage(response);
		}

}
