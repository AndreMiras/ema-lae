/*
 * This class is used to create some set of data in the database
 * so we can test the application manually from there.
 */

package database.util;

import dao.*;
import database.entity.*;
import exceptions.ContractException;
import exceptions.DaoException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URLClassLoader;
import java.net.URL;

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
            try {
                userProfileDao.create(userProfile);
            } catch (DaoException ex) {
                Logger.getLogger(InitDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public void createPermissions()
    {
        try {
            URL entitiesUrl = new URL("./src/database/entity/");
            URL[] myClassesPath = {entitiesUrl};
            URLClassLoader myClasses = new URLClassLoader(myClassesPath);
            System.out.println(myClasses.getClass().getName());
        } catch (MalformedURLException ex) {
            Logger.getLogger(InitDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
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
       ContractDao instance = new ContractDao();
        Users u1 = new Users("username1","pwd1");
        u1.setStaff(true);
        Users u2 = new Users("username2", "pwd2");
        u2.setSuperUser(true);
        Users u3 = new Users("username3", "pwd3");
        UserProfile p1 = new UserProfile(u1, UserProfile.Type.Apprentice);
        p1.setFirstName("BOEUF");
        p1.setLastName("Simon");
        UserProfile p2 = new UserProfile(u2, UserProfile.Type.InternshipSupervisor);
        p2.setFirstName("CATHALA");
        p2.setLastName("Philippe");
        UserProfile p3 = new UserProfile(u3, UserProfile.Type.SupervisingTeacher);
        p3.setFirstName("AMEGLIO");
        p3.setLastName("Frédéric");
        try{
            Contract pcontract = new Contract(p1, p2, p3);
            instance.create(pcontract);
        }
        catch(ContractException e){
            System.out.println("Bad contract");
        }

    }

        public void dropContracts()
    {
        Contract contract;
        ContractDao contractDao = new ContractDao();
        List contracts = contractDao.all();

        dropPermissions();
        for(int i=0; i<contracts.size(); i++)
        {
            contract = (Contract) contracts.get(i);
            contractDao.delete(contract);
        }
    }
}
