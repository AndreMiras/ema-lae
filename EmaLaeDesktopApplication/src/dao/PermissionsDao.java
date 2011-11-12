/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Permission;
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

}
