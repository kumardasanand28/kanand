/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.message.sender.stateless;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author BEAST
 */
@Remote
public interface MessageSenderRemote {
    
    
     void addNames(List names);
     
      List getNames();
      
      
      void postMessage();
    
}
