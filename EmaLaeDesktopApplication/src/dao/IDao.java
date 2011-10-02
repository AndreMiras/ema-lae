/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author andre
 */
public interface IDao<T> {

    /** Persist the newInstance object into database */
    Integer create(T obj);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(long id);

    /** Save changes made to a persistent object.  */
    void update(T obj);

    /** Remove an object from persistent storage in the database */
    void delete(T obj);

    /** find data set based on filters **/
    List<T> find(Hashtable<String, String> querySet);
}
