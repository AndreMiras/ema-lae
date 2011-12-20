/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.CourseSession;
import database.entity.Users;
import database.entity.UserProfile;
import exceptions.DaoException;
import java.util.HashMap;
import java.util.Set;

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

        @Override
    public void update(UserProfile p)
    {
        GenericDao<CourseSession> courseSessionGenericDao =
                new GenericDao<CourseSession>(CourseSession.class);
        UserProfile beforeUpdateProfile = read(p.getUserId());
        Set<CourseSession> beforeUpdateSessions =
                beforeUpdateProfile.getCourseSessions();

        for (CourseSession beforeUpdatedSession: beforeUpdateSessions)
        {
            /*
             * if the old session isn't part of the new sessions set,
             * updates it by making it orphan
             */
            if(!p.ownsSession(beforeUpdatedSession))
            {
                beforeUpdatedSession.setTeacher(null);
                courseSessionGenericDao.update(beforeUpdatedSession);
            }
        }
        super.update(p);
    }
}
