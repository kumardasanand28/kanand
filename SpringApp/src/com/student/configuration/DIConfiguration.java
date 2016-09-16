package com.student.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;

import com.student.repository.UserRepository;
import com.student.service.RegistrationService;

@Configuration
@ComponentScan(basePackages = {"com.student","com.student.repository"})
@Import({MessagingConfiguration.class,MessagingListnerConfiguration.class})
public class DIConfiguration {
	
	@Autowired
	JmsTemplate jmsTemplate;

	@Bean
	public RegistrationService getRegService(){
		RegistrationService regService = new RegistrationService();
		regService.setJmsTemplate(jmsTemplate);
		regService.setTestQueue(getQueue());
		return regService;
	}

	@Bean
	public ActiveMQQueue getQueue(){
		ActiveMQQueue queue = new ActiveMQQueue();
		queue.setPhysicalName("TestQueue");
		return queue;
	}
	
}
