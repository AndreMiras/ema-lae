/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.UserLae;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author amiras
 */
@Remote
public interface UserManagerSessionBeanRemote {

    boolean login(String username, String password);

    void addUser(String username, String password);

    Collection<UserLae> getAllUsers();
    
}
