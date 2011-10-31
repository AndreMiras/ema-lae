/*
 * This class is used to create some set of data in the database
 * so we can test the application manually from there.
 */

package database.util;

import dao.UserDao;
import dao.UserProfileDao;
import database.entity.User;
import database.entity.UserProfile;
import java.util.List;

/**
 *
 * @author andre
 */
public class InitDatabase {

    /**
     * Creates few profiles in the database
     */
    public void createProfiles()
    {
        User user;
        String username;
        String firstname;
        String lastname;
        UserProfile userProfile;
        UserProfileDao userProfileDao;

        userProfileDao = new UserProfileDao();
        username = "username";
        firstname = "firstname";
        lastname = "lastname";

        for(int i=0; i<3; i++)
        {
            user = new User(username + i);
            userProfile = new UserProfile(user);
            userProfile.setFirstName(firstname + i);
            userProfile.setLastName(lastname + i);

            userProfileDao.create(userProfile);
        }
    }

    /**
     * Drop all users and associated objects from the database
     */
    public void dropUsers()
    {
        User user;
        UserDao userDao = new UserDao();
        List users = userDao.all();
        
        for(int i=0; i<users.size(); i++)
        {
            user = (User) users.get(i);
            userDao.delete(user);
        }
    }

}
