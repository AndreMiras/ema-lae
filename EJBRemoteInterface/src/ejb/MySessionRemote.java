/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Remote;

/**
 *
 * @author amiras
 */
@Remote
public interface MySessionRemote {

    String getResult();

    void addUser(String username, String password);
    
}
