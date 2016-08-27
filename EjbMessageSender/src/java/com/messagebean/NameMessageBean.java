/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagebean;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author BEAST
 */
@MessageDriven(mappedName = "NameQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination",
            propertyValue = "/queue/NameQueue")
})
public class NameMessageBean implements MessageListener {

    public NameMessageBean() {
    }

    @Resource
    private MessageDrivenContext mdctx;

       @Override
    public void onMessage(Message message) {

        ObjectMessage objectMessage = null;
        try {
            objectMessage = (ObjectMessage) message;
            List<String> nameList = (List) objectMessage.getObject();
             System.out.println("Message driven Bean is working!!!");
            for(String names : nameList){
                System.out.println(names);
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
            mdctx.setRollbackOnly();
        }
    }

}
