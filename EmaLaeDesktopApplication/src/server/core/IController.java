/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.core;

import database.entity.User;
import database.entity.UserProfile;
import java.io.Serializable;


/**
 *
 * @author andre
 */
public interface IController extends java.rmi.Remote {


    /**
     * Tries to log the user in with the given credential.
     * @param username
     * @param password
     * @return true on login success, false otherwise
     * @throws java.rmi.RemoteException
     */
    public boolean login(String username, String password)
            throws java.rmi.RemoteException;

    /**
     * Only for testing purpose but will be taken out in production
     * Recreates some dataset in the database
     */
    public void initDatabaseForTests()
            throws java.rmi.RemoteException;

    /**
     * Returns the logged user
     * @return current logged in user, false if none
     * @throws java.rmi.RemoteException
     */
    public User getUser()
            throws java.rmi.RemoteException;

    /**
     * Returns the logged user profile
     * @returncurrent logged in user profile, false if none
     * @throws java.rmi.RemoteException
     */
    public UserProfile getUserProfile()
            throws java.rmi.RemoteException;

    public Serializable getEntityId(Object entity)
            throws java.rmi.RemoteException;

    public User[] getAllUsers() throws java.rmi.RemoteException;

    /**
     * TODO: this function will be generic later on
     * TODO: security checks, is the request.user allowed to do that
     * @param user
     * @throws java.rmi.RemoteException
     */
    public void updateUser(User user) throws java.rmi.RemoteException;

    public Serializable createUser(User user) throws java.rmi.RemoteException;

    public <T> void createOrUpdate(Class<T> type, T obj) throws java.rmi.RemoteException;
}
