/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.UserLae;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amiras
 */
@Remote(UserManagerSessionBeanRemote.class)
@Stateless
public class UserManagerSessionBean implements UserManagerSessionBeanRemote {
    @PersistenceContext(unitName="EntAppEJB-ejbPU")
    EntityManager em;
    // @PersistenceContext EntityManager em;

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
        System.out.println("TODO");
        em.persist(user);
    }

    @Override
    public List getAllUsers()
    {
        List userList = new ArrayList();
        // userList.add(new UserLae("foo","bar"));
        userList = em.createQuery("from UserLae u").getResultList();
        return userList;
    }
}
