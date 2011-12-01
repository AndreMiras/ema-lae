/*
 * This class is used to create some set of data in the database
 * so we can test the application manually from there.
 */

package database.util;

import dao.GroupDao;
import dao.PermissionsDao;
import dao.UserDao;
import dao.UserProfileDao;
import database.entity.UserGroup;
import database.entity.Permission;
import database.entity.Users;
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
        Users user;
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
            user = new Users(username + i);
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
        Users user;
        UserDao userDao = new UserDao();
        List users = userDao.all();
        
        for(int i=0; i<users.size(); i++)
        {
            user = (Users) users.get(i);
            userDao.delete(user);
        }
    }

    public void dropProfiles()
    {
        UserProfile profile;
        UserProfileDao userProfileDao = new UserProfileDao();
        List profiles = userProfileDao.all();

        for(int i=0; i<profiles.size(); i++)
        {
            profile = (UserProfile) profiles.get(i);
            userProfileDao.delete(profile);
        }
    }

    public void createGroups()
    {
        Permission permission;
        PermissionsDao permissionsDao;
        Users user = new Users();
        UserDao userDao = new UserDao();
        String permissionName = "permission";
        String groupName = "aGroup";
        UserGroup group;
        GroupDao groupDao;
        groupDao = new GroupDao();
        permissionsDao = new PermissionsDao();

        user = userDao.all().get(0);

        group = new UserGroup(groupName);
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
        UserGroup group;
        GroupDao groupDao = new GroupDao();
        List groups = groupDao.all();

        dropPermissions();
        for(int i=0; i<groups.size(); i++)
        {
            group = (UserGroup) groups.get(i);
            groupDao.delete(group);
        }
    }

}
