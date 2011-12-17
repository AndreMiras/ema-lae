/*
 * TODO:
 *      - add email to the model
 */

package database.entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author andre
 */
@Entity
public class Users implements Serializable, WithPrimaryKey {

    @Column(name="user_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    //Has access to admin GUI but actions have to be defined in the group permissions
    @Column
    private boolean staff;
    @Column
    //Has access to everything regardless his permissionsh
    private boolean superUser;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="users")
    private Set<UserGroup> groups;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="users")
    private Set<Permission> specialPermissions;

    public Users()
    {
        this.groups = new HashSet<UserGroup>();
        this.specialPermissions = new HashSet<Permission>();
    }

    public Users(String username)
    {
        this();
        this.username = username;

        // TODO: set random password when not specified
        this.password = "";
    }

    public Users(String username, String password)
    {
        this(username);
        setPassword(password);
    }

    public Set<UserGroup> getGroups() {
        return groups;
    }

    public boolean removeGroup(UserGroup g1)
    {
        g1.addUser(this); // workaround, seems required by mappedBy
        return this.groups.add(g1);
    }

   public void setGroups(Set<UserGroup> groups) {
        this.groups.clear();
        // perfs: could retro set user/group manually for better performances
        for (UserGroup group: groups)
        {
            addToGroup(group);
        }
    }

    public boolean addToGroup(UserGroup group){
        group.addUser(this); // workaround, seems required by mappedBy
        return this.groups.add(group);
    }


    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the password encrypted in the database
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = computeHashToString(password);
    }

    /**
     * Compute the hash from given plain string.
     * If the algorithm method isn't supported, returns a plain string byteArray
     * @param plain
     * @return
     */
    public static byte[] computeHash(String plain)// throws Exception
    {
        byte[] pwd;
        try // throws Exception
        {
            java.security.MessageDigest d = null;
            d = java.security.MessageDigest.getInstance("SHA-1");
            d.reset();
            d.update(plain.getBytes());
            pwd = d.digest();
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            pwd = plain.getBytes();
        }

        return pwd;
    }

    /**
     * Converts the byteArray hash back to hexaString
     * @param x
     * @return
     * @throws Exception
     */
    public static String computeHashToString(String x)
    {
        byte[] computedHash = computeHash(x);
        //convert the byte to hex format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < computedHash.length; i++) {
         sb.append(Integer.toString((computedHash[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Set<Permission> getSpecialPermissions() {
        return specialPermissions;
    }

    public void setSpecialPermissions(Set<Permission> specialPermissions) {
        this.specialPermissions = specialPermissions;
    }

    public boolean addSpecialPermission(Permission permission){
        permission.addUsers(this); // workaround, seems required by mappedBy
        return this.specialPermissions.add(permission);
    }

    @Override
    public String toString()
    {
        return username;
    }

    public boolean isStaff() {
        return staff;
    }

    public void setStaff(boolean isStaff) {
        this.staff = isStaff;
    }

    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean isSuperUser) {
        this.superUser = isSuperUser;
    }
    
    public boolean checkPermission (Permission permission){
        boolean hasPermission = false;
        Iterator<UserGroup> i=this.groups.iterator();
        while(i.hasNext() && !hasPermission)
        {
            UserGroup userGroup = i.next();
            Iterator<Permission> j = userGroup.getPermissions().iterator();
            while(j.hasNext() && !hasPermission){
                hasPermission = j.next().containsGroup(userGroup);
            }
        }
        Iterator<Permission> k = this.specialPermissions.iterator();
        while(k.hasNext() && !hasPermission){
            hasPermission = k.next().getPermissionId().equals(permission.getPermissionId());
        }
        return hasPermission;
    }

    public boolean checkPermission (String permission){
        boolean hasPermission = false;
        Iterator<UserGroup> i=this.groups.iterator();
        while(i.hasNext() && !hasPermission)
        {
            UserGroup userGroup = i.next();
            Iterator<Permission> j = userGroup.getPermissions().iterator();
            while(j.hasNext() && !hasPermission){
                hasPermission = j.next().getName().equals(permission);
            }
        }
        Iterator<Permission> k = this.specialPermissions.iterator();
        while(k.hasNext() && !hasPermission){
            hasPermission = k.next().getName().equals(permission);
        }
        return hasPermission;
    }

    public boolean containsGroup(UserGroup group){
        Iterator<UserGroup> it = this.groups.iterator();
        boolean foundGroup = false;
        while(!foundGroup && it.hasNext()){
            foundGroup = group.getGroupId().equals(it.next().getGroupId());
        }
        return foundGroup;
    }

    public Serializable getPrimaryKey()
    {
        return userId;
    }

    private void clearGroups()
    {
        for (UserGroup group: groups)
        {
            group.removeUser(this);
        }
        this.groups.clear();
    }
    
}
