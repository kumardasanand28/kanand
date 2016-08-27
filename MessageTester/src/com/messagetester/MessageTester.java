/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagetester;

/**
 *
 * @author BEAST
 */
import com.message.sender.stateless.MessageSenderRemote;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageTester {

    BufferedReader brConsoleReader = null;
    Properties props;
    InitialContext ctx;

    {
        props = new Properties();
        try {
            props.load(new FileInputStream("jndi.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MessageTester ejbTester = new MessageTester();

        ejbTester.postMessageToEJB();
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Enter Message To POST");
        System.out.println("**********************");
        System.out.print("Enter the name comma separated to send as message ");
    }

    private void postMessageToEJB() {
        try {

            MessageSenderRemote libraryBean
                    = (MessageSenderRemote) ctx.lookup("MessageSender/remote");
            String names;
            showGUI();
            System.out.print("Enter the name: ");
            names = brConsoleReader.readLine();
            libraryBean.addNames(Arrays.asList(names.split(",")));
             System.out.print("Posting Message!!! ");
            libraryBean.postMessage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (brConsoleReader != null) {
                    brConsoleReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
