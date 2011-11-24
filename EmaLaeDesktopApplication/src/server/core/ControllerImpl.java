/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import dao.GenericDao;
import dao.UserDao;
import dao.UserProfileDao;
import database.entity.User;
import database.entity.UserProfile;
import database.util.HibernateUtil;
import database.util.InitDatabase;
import java.io.Serializable;
import java.rmi.RemoteException;
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

    public Serializable getEntityId(Object entity) throws RemoteException
    {
        Serializable id =
                HibernateUtil.getSessionFactory().openSession().getIdentifier(entity);
        return id;
    }


    /**
     * FIXME: verify the logged user has the right to do so
     * @return
     * @throws RemoteException
     */
    public User[] getAllUsers() throws RemoteException
    {
        UserDao userDao = new UserDao();
        List<User> userList;
        userList = userDao.all();
        User users[] = new User[userList.size()];

        return userList.toArray(users);
    }

    public void updateUser(User user) throws RemoteException
    {
        // UserDao userDao = new UserDao();
        // userDao.update(user);
        GenericDao<User> userDao = new GenericDao<User>(User.class);
        userDao.update(user);
    }

    public Serializable createUser(User user) throws RemoteException
    {
        Serializable pk;

        UserDao userDao = new UserDao();
        pk = userDao.create(user);

        return pk;
    }

    public <T> void createOrUpdate(Class<T> type, T obj) throws RemoteException
    {
        // UserDao userDao = new UserDao();
        // userDao.createOrUpdate(user);

        GenericDao<T> genericDao = new GenericDao<T>(type);
        genericDao.createOrUpdate(obj);
    }

    public <T> T[] getAllObjects(Class<T> type) throws RemoteException
    {
        GenericDao<T> genericDao = new GenericDao<T>(type);
        List<T> objectList;
        objectList = genericDao.all();

        return (T[]) objectList.toArray();
    }
}
