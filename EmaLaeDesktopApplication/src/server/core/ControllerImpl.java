/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import dao.UserDao;
import database.entity.User;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author laurent
 */
public class ControllerImpl extends java.rmi.server.UnicastRemoteObject
        implements IController
    {

    protected User loggedUser;

    public ControllerImpl() throws java.rmi.RemoteException, java.io.IOException {
        System.out.println("Constructor");
    }

    public boolean login(String username, String password)
            throws java.rmi.RemoteException {
        System.out.println("Trying to login with:\n"
                + "username:"
                + username
                + "\n"
                + "password:"
                + password
                );

        UserDao userDao = new UserDao();
        Hashtable<String, String> querySet = new Hashtable<String, String>();
        querySet.put("username", username);


        List<User> userList;
        userList = userDao.all();
        System.out.println("Debug (userlist):");
        for(int i=0; i<userList.size(); i++)
        {
            System.out.println("User:"
                    + userList.get(i).getUsername()
                    + "\n"
                    + "password:"
                    + userList.get(i).getPassword()
                    );
        }
        userList = userDao.find(querySet);
        if (userList.size() > 0)
        {
            System.out.println("Logged in!");
            loggedUser = userList.get(0);
            return true;
        }
        else
        {
            System.out.println("Not logged in!");
        }

        return false;
    }
}
