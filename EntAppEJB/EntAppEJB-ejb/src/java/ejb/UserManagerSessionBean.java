/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.UserLae;
import java.util.Collection;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author amiras
 */
@Remote(UserManagerSessionBeanRemote.class)
@Stateless
public class UserManagerSessionBean implements UserManagerSessionBeanRemote {
    // @PersistenceContext(unitName="EntityBean")
    EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean login(String username, String password)
    {
        return false; // TODO
    }

    @Override
    public void addUser(String username, String password)
    {
        UserLae user = new UserLae(username, password);
        em.persist(user);
    }

    @Override
    public Collection<UserLae> getAllUsers()
    {
        Collection<UserLae> userList;
        userList = em.createQuery("from UserLae u").getResultList();
        return userList;
    }
}
