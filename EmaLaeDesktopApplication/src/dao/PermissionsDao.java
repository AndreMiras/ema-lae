/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Permission;
import exceptions.DaoException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author pc
 */
public class PermissionsDao extends DaoHibernate<Permission, Integer> {
    public PermissionsDao()
    {
        super(Permission.class);
    }

    public PermissionsDao(Class<Permission> type)
    {
        super(type);
    }

    /*
     * Fails silently
     */
    @Override
    public Integer create(Permission p){
        Integer pk = null;
        try {
            pk = super.create(p);
        } catch (DaoException ex) {
            Logger.getLogger(PermissionsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return pk;
        }
    }

}
