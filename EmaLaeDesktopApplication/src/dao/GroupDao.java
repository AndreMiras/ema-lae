/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Group;

/**
 *
 * @author pc
 */
public class GroupDao extends DaoHibernate<Group, Integer> {

    public GroupDao()
    {
        super(Group.class);
    }

    public GroupDao(Class<Group> type)
    {
        super(type);
    }
}
