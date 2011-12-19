/*
 * This class is used to create some set of data in the database
 * so we can test the application manually from there.
 */

package database.util;

import dao.*;
import database.entity.*;
import exceptions.ContractException;
import exceptions.DaoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.util.HashSet;
import java.util.Set;


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
            Permission p1 = new Permission("Users_create");
            Permission p2 = new Permission("Users_update");
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
        addApprenticesToPromotions(); // adds few apprentices to a promotion
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
        Users user = new Users("admin", "pwd");
        user.setSuperUser(true);
        UserDao userDao = new UserDao();
        String groupName = "bGroup";
        UserGroup group = new UserGroup(groupName);
        group.addUser(user);
        GroupDao groupDao = new GroupDao();
        
        userDao.create(user);
        groupDao.create(group);
        Permission permission = new Permission();
        PermissionsDao permissionsDao = new PermissionsDao();
        HashSet<String> classes = getClassesName();
        for(String currentClass : classes)
        {
            if (!currentClass.equals("WithPrimaryKey"))
            {
                permission.setName(currentClass+"_read");
                permissionsDao.create(permission);
                group.addPermission(permission);
                groupDao.update(group);
                permission.setName(currentClass+"_update");
                permissionsDao.create(permission);
                group.addPermission(permission);
                groupDao.update(group);
                permission.setName(currentClass+"_create");
                permissionsDao.create(permission);
                group.addPermission(permission);
                groupDao.update(group);
                permission.setName(currentClass+"_delete");
                permissionsDao.create(permission);
                group.addPermission(permission);
                groupDao.update(group);
            }
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

        // dropPermissions();
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

        // dropPermissions();
        for(int i=0; i<contracts.size(); i++)
        {
            contract = (Contract) contracts.get(i);
            contractDao.delete(contract);
        }
    }

    /**
     * creates some formation sets and attaches them to the first
     * promotion found
     */
    public void createFormations()
    {
        FormationDao formationDao = new FormationDao();
        PromotionDao promotionDao = new PromotionDao();

        Promotion promotion;
        List<Promotion> promotions = promotionDao.all();
        if (promotions.size() == 0)
        {
            createPromotions();
            promotions = promotionDao.all();
        }
        promotion = promotions.get(0);
        Formation semester1Formation = new Formation("Semester1");
        Formation semester2Formation = new Formation("Semester2");
        semester1Formation.setPromotion(promotion);
        semester2Formation.setPromotion(promotion);

        Formation mathematicsFormation = new Formation("Mathematics");
        mathematicsFormation.setParentFormation(semester1Formation);

        Formation philosophyFormation = new Formation("Philosophy");
        philosophyFormation.setParentFormation(semester1Formation);

        Formation spanishFormation = new Formation("Spanish");
        spanishFormation.setParentFormation(semester1Formation);

        formationDao.create(semester1Formation); // CASCADE
        formationDao.create(semester2Formation);

        /* CASCADE
        formationDao.create(mathematicsFormation);
        formationDao.create(philosophyFormation);
        formationDao.create(spanishFormation);
         */
    }

    public void dropFormations()
    {
        Formation formation;
        FormationDao formationDao = new FormationDao();
        // List formations = formationDao.all();

        /*
         * Always refreshes the list lest formations as they get deleted.
         * Children formations are getting deleted CASCADE
         */
        for(int i=0; i<formationDao.all().size(); i++)
        {
            formation = (Formation) formationDao.all().get(i);
            formationDao.delete(formation);
        }
    }

    /**
     * This method adds few apprentices to the first promotion found
     */
    public void addApprenticesToPromotions()
    {
        List<UserProfile> userProfiles;
        Set<UserProfile> userProfilesSet;
        UserProfileDao userProfileDao = new UserProfileDao();
        PromotionDao promotionDao = new PromotionDao();
        Promotion promotion;
        List<Promotion> promotions = promotionDao.all();
        // creates few promotions if none exist
        if (promotions.size() == 0)
        {
            createPromotions();
        }
        promotion = promotions.get(0);

        // TODO: filter to take apprentices only rather than all profiles types
        userProfiles = userProfileDao.all();
        userProfilesSet = new HashSet(userProfiles);
        promotion.setApprentices(userProfilesSet);
        promotionDao.update(promotion);
        promotion = promotions.get(0);
        System.out.println("promotion:" + promotion);
    }

    public void createPromotions()
    {
        GenericDao<Promotion> promotionDao =
                new GenericDao<Promotion>(Promotion.class);
        String promoName = "InfRes";
        Integer promoYear = 2010;
        Promotion promotion;
        for (int i=0; i<3; i++)
        {
            promotion = new Promotion(promoName + (i + 1));
            promotion.setPromotionYear(promoYear + i);
            try
            {
                promotionDao.create(promotion);
            }
            catch (DaoException ex)
            {
                Logger.getLogger(InitDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public void dropPromotions()
    {
        Promotion promo;
        GenericDao<Promotion> promotionDao =
                new GenericDao<Promotion>(Promotion.class);
        List<Promotion> promotions = promotionDao.all();

        for(int i=0; i<promotions.size(); i++)
        {
            promo = promotions.get(i);
            promotionDao.delete(promo);
        }
    }

    private HashSet getClassesName()
    {
        File folder = new File("./src/database/entity");
        File[] listOfFiles = folder.listFiles();
        HashSet<String> classes = new HashSet<String>();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            String name = listOfFiles[i].getName();
            name = name.replace(".java", "");
            if (listOfFiles[i].isFile())
            {
                classes.add(name);
            }
        }
        return classes;
    }
}
