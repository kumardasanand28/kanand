package com.student.reciever;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.student.entity.UserEntity;
import com.student.repository.UserRepository;
import com.student.springjpa.service.UserService;  

public class RegistrationMessageReciever implements MessageListener {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	public void onMessage(Message message) {
		if(message instanceof TextMessage){
			try {
				String msgText = ((TextMessage) message).getText();
				System.out.println(msgText);
				UserEntity user = parseMessage(msgText);
				//getUserRepository().insert(user);
				userService.create(user);
			} catch (JMSException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private UserEntity parseMessage(String msgText) {

		UserEntity user = null;
		try {
			JSONObject obj = new JSONObject(msgText);

			String studentName = obj.getString("studentName");
			String studentHobby = obj.getString("studentHobby");
			long studentMobile = obj.getLong("studentMobile");
			String studentDOB = obj.getString("studentDOB");
			JSONArray studentSkills = obj.getJSONArray("studentSkills");

			String studentSkill = "";
			for (int i = 0; i < studentSkills.length(); i++) {
				studentSkill+=studentSkills.getString(i);
				if(studentSkills.length() != i-1){
					studentSkill+=",";
				}

			}
			//Oct 10, 2010 12:00:00 AM
			DateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
			java.util.Date dutilDate = df.parse(studentDOB);
			user = new UserEntity(studentName,studentHobby,Long.toString(studentMobile),new Date(dutilDate.getTime()),studentSkill);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


}
