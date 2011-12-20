/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import exceptions.DaoException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author andre
 */
public interface IDao<T, PK extends Serializable> {

    /** Persist the newInstance object into database */
    PK create(T obj)
            throws DaoException;

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(PK id);

    /** Save changes made to a persistent object.  */
    void update(T obj);

    /** Tries to update the object if it already exists otherwise creates it  */
    public void createOrUpdate(T obj);

    /** Remove an object from persistent storage in the database */
    void delete(T obj);

    /** Find data sets based on filters **/
    List<T> find(HashMap<String, String> querySet);

    /** Return the only one record found  for a givent filter **/
    T get(HashMap<String, String> querySet)
            throws DaoException;

    /** get all sets **/
    List<T> all();
}
