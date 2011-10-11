/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;

/**
 *
 * @author amiras
 */
@Stateless
public class UserManagerSessionBean implements UserManagerSessionBeanRemote {

    @Override
    public boolean login(String username, String password) {
        
        return false; // TODO
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
