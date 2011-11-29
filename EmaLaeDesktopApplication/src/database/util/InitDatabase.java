/*
 * This class is used to create some set of data in the database
 * so we can test the application manually from there.
 */

package database.util;

import dao.GroupDao;
import dao.PermissionsDao;
import dao.UserDao;
import dao.UserProfileDao;
import database.entity.Group;
import database.entity.Permission;
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
        String password;
        String firstname;
        String lastname;
        UserProfile userProfile;
        UserProfileDao userProfileDao;

        userProfileDao = new UserProfileDao();
        username = "username";
        password = "pwd";
        firstname = "firstname";
        lastname = "lastname";

        for(int i=0; i<3; i++)
        {
            user = new User(username + i);
            user.setPassword(password + i);
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

    public void createGroups()
    {
        Permission permission;
        PermissionsDao permissionsDao;
        User user = new User();
        UserDao userDao = new UserDao();
        String permissionName = "permission";
        String groupName = "aGroup";
        Group group;
        GroupDao groupDao;
        groupDao = new GroupDao();
        permissionsDao = new PermissionsDao();

        user = userDao.all().get(0);

        group = new Group(groupName);
        // group.addUser(user); // FIXME: fails
        for(int i=0; i<3; i++)
        {
            permission = new Permission(permissionName + i);
            permissionsDao.create(permission);
            // group.addPermission(permission); // FIXME: fails
        }
        groupDao.create(group);
    }

    public void dropPermissions()
    {
        Permission permission;
        PermissionsDao permissionsDao = new PermissionsDao();
        List permissions = permissionsDao.all();

        for(int i=0; i<permissions.size(); i++)
        {
            permission = (Permission) permissions.get(i);
            permissionsDao.delete(permission);
        }
    }

    public void dropGroups()
    {
        Group group;
        GroupDao groupDao = new GroupDao();
        List groups = groupDao.all();

        dropPermissions();
        for(int i=0; i<groups.size(); i++)
        {
            group = (Group) groups.get(i);
            groupDao.delete(group);
        }
    }

}
