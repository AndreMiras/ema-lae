/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Users;
import database.entity.UserProfile;
import exceptions.DaoException;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author andre
 */
public class UserProfileDao extends DaoHibernate<UserProfile, Integer> {

    public UserProfileDao()
    {
        super(UserProfile.class);
    }

    public UserProfileDao(Class<UserProfile> type)
    {
        super(type);
    }

    /**
     *
     * @param user
     * @return an userprofile object from its user object foreign key
     */

    public UserProfile get(Users user) throws DaoException
    {
        HashMap querySet = new HashMap<String, String>();
        querySet.put("user_id", user.getUserId().toString());
        return super.get(querySet);
    }

    @Override
    public Integer create(UserProfile profile) throws DaoException{
        if(profile.getUser() == null)
            throw new DaoException("Can not create userprofile without user");
        else
            return super.create(profile);
    }

}
