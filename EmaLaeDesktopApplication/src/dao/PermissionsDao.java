/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Permission;
import database.entity.UserGroup;
import exceptions.DaoException;
import java.util.Set;
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

    /**
     * Explicitely updates the many-to-many "groups" field.
     * This is required because Users doesn't own the m2m relation.
     * This is mainly used when performing a user.setGroups(newGroupSet)
     * followed by a userDao.save(user)
     * @param permission
     */
    @Override
    public void update(Permission permission)
    {
        GroupDao groupDao = new GroupDao();
        Permission beforeUpdatePermission = read(permission.getPermissionId());
        Set<UserGroup> beforeUpdateGroups =
                beforeUpdatePermission.getGroups();

        for (UserGroup beforeUpdateGroup: beforeUpdateGroups)
        {
            /*
             * if the old group isn't part of the new group set,
             * updates it by removing the previous associated user.
             */
            if(!permission.containsGroup(beforeUpdateGroup))
            {
                beforeUpdateGroup.removePermission(permission);
                groupDao.update(beforeUpdateGroup);
            }
        }
        super.update(permission);
    }
}
