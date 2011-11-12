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
public class PermissionDao extends DaoHibernate<Permission, Integer> {

    public PermissionDao()
    {
        super(Permission.class);
    }

    public PermissionDao(Class<Permission> type)
    {
        super(type);
    }

}
