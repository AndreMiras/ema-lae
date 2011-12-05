/*
 * This class is used to create some set of data in the database
 * so we can test the application manually from there.
 */

package database.util;

import dao.*;
import database.entity.*;
import exceptions.ContractException;
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
            // Attribute a different type each user
            switch (i%3){
                case 1:
                    userProfile.setUserProfileType(UserProfile.Type.Apprentice);
                    break;
                case 2:
                    userProfile.setUserProfileType(UserProfile.Type.InternshipSupervisor);
                    break;
                case 3:
                    userProfile.setUserProfileType(UserProfile.Type.SupervisingTeacher);
                    break;
            }
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
        List<Users> users = userDao.all();
        
        for(int i=0; i<users.size(); i++)
        {
            user = users.get(i);
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
        group.addUser(user);
        for(int i=0; i<3; i++)
        {
            permission = new Permission(permissionName + i);
            permissionsDao.create(permission);
            group.addPermission(permission);
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

    public void createContracts()
    {
        UserProfileDao userProfileDao;
        ContractDao contractDao;
        Users user;
        String username;
        String password;
        String firstname;
        String lastname;
        UserProfile userProfile1;
        UserProfile userProfile2;
        UserProfile userProfile3;
        Contract contract;

        userProfileDao = new UserProfileDao();
        contractDao = new ContractDao();
        username = "username";
        password = "pwd";
        firstname = "firstname";
        lastname = "lastname";


        user = new Users(username+'1');
        user.setPassword(password+'1');
        userProfile1 = new UserProfile(user);
        userProfile1.setFirstName(firstname + '1');
        userProfile1.setLastName(lastname + '1');
        // Attribute a different type each user
        userProfile1.setUserProfileType(UserProfile.Type.Apprentice);
        Integer id = userProfileDao.create(userProfile1);
        
        user = new Users(username+'2');
        user.setPassword(password+'2');
        userProfile2 = new UserProfile(user);
        userProfile2.setFirstName(firstname + '2');
        userProfile2.setLastName(lastname + '2');
        // Attribute a different type each user
        userProfile2.setUserProfileType(UserProfile.Type.InternshipSupervisor);
        userProfileDao.create(userProfile2);
        
        user = new Users(username+'3');
        user.setPassword(password+'3');
        userProfile3 = new UserProfile(user);
        userProfile3.setFirstName(firstname + '3');
        userProfile3.setLastName(lastname + '3');
        // Attribute a different type each user
        userProfile3.setUserProfileType(UserProfile.Type.SupervisingTeacher);
        userProfileDao.create(userProfile3);


        try{
            contract = new Contract(userProfile1, userProfile2, userProfile3);
            contractDao.create(contract);
            userProfile1 = userProfileDao.read(id);
            System.out.println("");
        }
        catch(ContractException e){
            System.out.println("Bad contract");
        }

    }

}
