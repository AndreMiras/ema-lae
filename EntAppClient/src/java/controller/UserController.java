/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.UserManagerSessionBeanRemote;
import entity.UserLae;
import java.util.Collection;
import javax.ejb.EJB;

/**
 *
 * @author amiras
 */
public class UserController
{
    @EJB
    private static UserManagerSessionBeanRemote userManager;

    public void createUser(String username, String password)
    {
        userManager.addUser(username, password);
    }
    
    public Collection<UserLae> getAllUsers()
    {
        return userManager.getAllUsers();
    }
}
