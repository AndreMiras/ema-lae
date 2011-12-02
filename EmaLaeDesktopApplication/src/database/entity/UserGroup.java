/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author pc
 */

@Entity
public class UserGroup implements Serializable{

    @Column(name ="group_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer groupId;
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        joinColumns =
        {@JoinColumn},
        inverseJoinColumns = {@JoinColumn}
    )
    private Set<Users> users;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        joinColumns =
        {@JoinColumn(name = "groups_id") },
        inverseJoinColumns = { @JoinColumn(name = "permissions_id") }
    )
    private Set<Permission> permissions;

    public UserGroup()
    {
        this.users = new HashSet<Users>();
        this.permissions = new HashSet<Permission>();
    }


    public UserGroup(String groupName){
        this();
       this.name = groupName;
    }

    public Integer getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Integer ID) {
        this.groupId = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public Set<Permission> getPermissions() {
        return permissionsID;
    }

    public void setPermissions(Set<Permission> permissionsID) {
        this.permissionsID = permissionsID;
    }
     */

    // meta-widget better deals with Lists at the moment
    public List<Permission> getPermissions()
    {
        List<Permission> permissionList =
                new ArrayList<Permission>(permissions);
        return permissionList;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = new HashSet<Permission>(permissions);
    }

    public boolean addPermission(Permission perm){
        return this.permissions.add(perm);
    }

    public boolean addUser(Users u)
    {
        return users.add(u);
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public boolean containsUser(Users user){
        boolean foundUser = false;
        Iterator<Users> it = this.users.iterator();
        while(!foundUser && it.hasNext()){
            foundUser = user.getUserId().equals(it.next().getUserId());
        }
        return foundUser;
    }

    public boolean containsPermission(Permission permission){
        boolean foundPermission = false;
        Iterator<Permission> it = this.permissions.iterator();
        while(!foundPermission && it.hasNext()){
            foundPermission = permission.getPermissionId().equals(it.next().getPermissionId());
        }
        return foundPermission;
    }

}
