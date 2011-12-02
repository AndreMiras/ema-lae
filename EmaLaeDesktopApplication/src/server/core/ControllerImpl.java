/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import dao.GenericDao;
import dao.UserDao;
import dao.UserProfileDao;
import database.entity.Users;
import database.entity.UserProfile;
import database.util.HibernateUtil;
import database.util.InitDatabase;
import exceptions.DaoException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laurent
 */
public class ControllerImpl extends java.rmi.server.UnicastRemoteObject
        implements IController
    {

    protected Users loggedUser;

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
        initDatabase.dropGroups();
        initDatabase.createProfiles();
        initDatabase.createGroups();
    }

    public boolean login(String username, String password)
            throws java.rmi.RemoteException {
        Boolean loginSuccess = false;
        UserDao userDao = new UserDao();
        HashMap<String, String> querySet = new HashMap<String, String>();
        querySet.put("username", username);
        querySet.put("password", password);

        List<Users> userList;
        userList = userDao.all();

        userList = userDao.find(querySet); // TODO: use a get instead
        if (userList.size() > 0)
        {
            loggedUser = userList.get(0);
            loginSuccess = true;
        }

        return loginSuccess;
    }

    public Users getUser()
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
            try
            {
                userProfile = (UserProfile) userProfileDao.get(loggedUser);
            } catch (DaoException ex)
            {
                Logger.getLogger(ControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return userProfile;
    }

    public Serializable getEntityId(Object entity) throws RemoteException
    {
        Serializable id =
                HibernateUtil.getSessionFactory().openSession().getIdentifier(entity);
        return id;
    }

    public <T> void createOrUpdate(Class<T> type, T obj) throws RemoteException
    {
        // UserDao userDao = new UserDao();
        // userDao.createOrUpdate(user);

        GenericDao<T> genericDao = new GenericDao<T>(type);
        genericDao.createOrUpdate(obj);
    }


    /**
     * FIXME: verify the logged user has the right to do so
     * @return
     * @throws RemoteException
     */
    public <T> T[] getAllObjects(Class<T> type) throws RemoteException
    {
        GenericDao<T> genericDao = new GenericDao<T>(type);
        List<T> objectList;
        objectList = genericDao.all();

        return (T[]) objectList.toArray();
    }

    public <T> void delete(Class<T> type, T obj) throws RemoteException
    {
        GenericDao<T> genericDao = new GenericDao<T>(type);
        genericDao.delete(obj);
    }
}
