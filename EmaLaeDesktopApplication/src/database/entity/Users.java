/*
 * TODO:
 *      - add email to the model
 */

package database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author andre
 */
@Entity
public class Users implements Serializable {

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
    private boolean isStaff;
    @Column
    //Has access to everything regardless his permissionsh
    private boolean isSuperUser;

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
        this.password = password;
    }

    // meta-widget better deals with List Collections at the moment
    public List<UserGroup> getGroups() {
        List<UserGroup> groupList = new ArrayList<UserGroup>(groups);
        return groupList;
    }

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }

    public boolean addToGroup(UserGroup newGroup){
        newGroup.addUser(this); // workaround, seems required by mappedBy
        return this.groups.add(newGroup);
    }


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public List<Permission> getSpecialPermissions() {
        List<Permission> listPermissions = (ArrayList<Permission>) specialPermissions;
        return listPermissions;
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

    public boolean isIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }

    public boolean isIsSuperUser() {
        return isSuperUser;
    }

    public void setIsSuperUser(boolean isSuperUser) {
        this.isSuperUser = isSuperUser;
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

    public boolean containsGroup(UserGroup group){
        Iterator<UserGroup> it = this.groups.iterator();
        boolean foundGroup = false;
        while(!foundGroup && it.hasNext()){
            foundGroup = group.getGroupId().equals(it.next().getGroupId());
        }
        return foundGroup;
    }
    
}
