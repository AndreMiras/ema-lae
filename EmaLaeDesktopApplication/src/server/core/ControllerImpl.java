/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import dao.UserDao;
import dao.UserProfileDao;
import database.entity.User;
import database.entity.UserProfile;
import database.util.InitDatabase;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author laurent
 */
public class ControllerImpl extends java.rmi.server.UnicastRemoteObject
        implements IController
    {

    protected User loggedUser;

    public ControllerImpl() throws java.rmi.RemoteException, java.io.IOException
    {
    }

    /**
     * Creates few user profiles test sets
     * @throws java.rmi.RemoteException
     */
    public void initDatabaseForTests()
            throws java.rmi.RemoteException {
        InitDatabase initDatabase = new InitDatabase();
        initDatabase.dropUsers();
        initDatabase.createProfiles();
    }

    public boolean login(String username, String password)
            throws java.rmi.RemoteException {
        Boolean loginSuccess = false;
        UserDao userDao = new UserDao();
        HashMap<String, String> querySet = new HashMap<String, String>();
        querySet.put("username", username);
        querySet.put("password", password);

        List<User> userList;
        userList = userDao.all();

        userList = userDao.find(querySet);
        if (userList.size() > 0)
        {
            loggedUser = userList.get(0);
            loginSuccess = true;
        }

        return loginSuccess;
    }

    public User getUser()
            throws java.rmi.RemoteException
    {
        return loggedUser;
    }

    public UserProfile getUserProfile()
            throws java.rmi.RemoteException
    {
        UserProfile userProfile = null;
        UserProfileDao userProfileDao = new UserProfileDao();

        if (loggedUser != null)
        {
            userProfile = (UserProfile) userProfileDao.get(loggedUser);
        }

        return userProfile;
    }
}
