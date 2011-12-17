/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import dao.GenericDao;
import dao.UserDao;
import dao.UserProfileDao;
import dao.FormationDao;
import database.entity.Permission;
import database.entity.Formation;
import database.entity.Users;
import database.entity.UserProfile;
import database.util.HibernateUtil;
import database.util.InitDatabase;
import emalaedesktopapplication.utils.Utils;
import exceptions.DaoException;
import exceptions.PermissionException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This RMI class provides some interfaces to the model
 */
public class ControllerServiceImpl extends java.rmi.server.UnicastRemoteObject
        implements IControllerService
    {

    protected Users loggedUser;

    ControllerServiceImpl() throws java.rmi.RemoteException, java.io.IOException
    {
    }

    /**
     * Creates few user profiles test sets
     * @throws java.rmi.RemoteException
     */
    public void initDatabaseForTests()
            throws java.rmi.RemoteException {
        InitDatabase initDatabase = new InitDatabase();
        initDatabase.dropContracts();
        initDatabase.dropFormations();
        initDatabase.dropPermissions();
        initDatabase.dropPromotions();
        initDatabase.dropUsers();
        initDatabase.dropGroups();
        initDatabase.createContracts();
        initDatabase.createGroups();
        initDatabase.createFormations();
        initDatabase.createPermissions();
        initDatabase.createPromotions();
    }

    public boolean login(String username, String password)
            throws java.rmi.RemoteException {
        Boolean loginSuccess = false;
        UserDao userDao = new UserDao();
        String passwordHash = Users.computeHashToString(password);
        HashMap<String, String> querySet = new HashMap<String, String>();
        querySet.put("username", username);
        querySet.put("password", passwordHash);

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
                Logger.getLogger(ControllerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
        Users user = null;
        if (loggedUser != null)
        {
            try
            {

                user = getUser();
                GenericDao<T> genericDao = new GenericDao<T>(type);
                String className = Utils.getClassNameWithoutPackage(type);
                if(user.checkPermission(className+"_create")&&user.checkPermission(className+"_update"))
                {
                    genericDao.createOrUpdate(obj);
                }
                else throw new PermissionException("Permission denied");
            }
            catch (RemoteException ex)
            {
                Logger.getLogger(ControllerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else //FIXME : Debug and test purpose only : needs to be removed in production
        {
            GenericDao<T> genericDao = new GenericDao<T>(type);
            genericDao.createOrUpdate(obj);
        }
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
        /*
         * FIXME : Implement permissions on DAO methods
        String permString = type.getName() + ".delete";
        Permission permission = new Permission(permString);
        assert(loggedUser.checkPermission(permission));
         */
        GenericDao<T> genericDao = new GenericDao<T>(type);
        genericDao.delete(obj);
    }

    public Formation getFormation() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T> void get(Class<T> type, T obj) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
