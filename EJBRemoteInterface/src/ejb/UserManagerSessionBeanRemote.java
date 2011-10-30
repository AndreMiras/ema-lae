/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author amiras
 */
@Remote
public interface UserManagerSessionBeanRemote {

    boolean login(String username, String password);

    void addUser(String username, String password);

    List getAllUsers();

    String getResultTest();

    Collection getAllUsersTest();
    
}
