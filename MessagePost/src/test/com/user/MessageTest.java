package test.com.user;

import java.io.IOException;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.messagesender.MessageSender;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.stateful.UserStateFul;
import com.user.UserDataServlet;

public class MessageTest {

	private UserDataServlet userServlet;
	private MockHttpServletRequest mRequest;
	private MockHttpServletResponse mResponse;
	MessageSender messageSender;
	UserStateFul userStateful;
	Context context;

	@Before
	public void setUp(){
		userServlet = new UserDataServlet();
		mRequest = new MockHttpServletRequest();
		mResponse = new MockHttpServletResponse();
		messageSender = new MessageSender();
		try {
			context = getInitialContext();
			messageSender.setInititalContext(context);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userServlet.setMessageSender(messageSender);

		userStateful = new UserStateFul();
		userServlet.setUserStateful(userStateful);
	}
	@Test
	public void test_Entered_CorrectData() throws ServletException, IOException {
		System.out.println("Started");
		mRequest.addParameter("fullname", "ANAND JUNIT");
		mRequest.addParameter("empid", "94957");


		//userServlet.doPost(mRequest,mResponse);
		//userServlet.doGet(mRequest,mResponse);

		System.out.println(mResponse);



		WebConversation conversation = new WebConversation();
		WebRequest request = new GetMethodWebRequest("http://localhost:8080/MessagePost/" );
		  try {
			WebResponse response = conversation.getResponse( request );
			 WebForm form = response.getForms()[0];
			 request = form.getRequest();
			 request.setParameter( "name", "ANAND JUNIT" );
			 request.setParameter( "empid", "94957" );
			 response = conversation.getResponse( request );
			 System.out.println(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*try {
			MessageSender sender = (MessageSender) context.lookup("java:global/Sample/EJBMessageSend/MessageSender!com.messagesender.MessageSender");
			sender.postMessage( "ANAND JUNIT", "94957");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


	private Context getInitialContext()
			throws javax.naming.NamingException {

		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080/");
		//This property is important for remote resolving
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		return new javax.naming.InitialContext(jndiProperties);
	}
}
