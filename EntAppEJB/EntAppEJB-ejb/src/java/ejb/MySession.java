/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.UserLae;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author amiras
 */
@Stateless
public class MySession implements MySessionRemote {
    
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public String getResult() {
        return "Foobar";
    }

    @Override
    public void addUser(String username, String password)
    {
        UserLae user = new UserLae(username, password);
        em.persist(user);
    }
    
}
