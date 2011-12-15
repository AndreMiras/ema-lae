/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author pc
 */

@Entity
public class UserGroup implements Serializable, WithPrimaryKey {

    @Column(name ="group_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer groupId;
    @Column
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        joinColumns = @JoinColumn (referencedColumnName="group_id"),
        inverseJoinColumns = @JoinColumn (referencedColumnName="user_id")
        )
    private Set<Users> users;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
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
     */

    public void setPermissions(Set<Permission> permissions) {
        removePermissions();
         // perfs: could retro set user/group manually for better performances
        for (Permission permission: permissions)
        {
            addPermission(permission);
        }
    }

    public Set<Permission> getPermissions()
    {
        return permissions;
    }

    /*
    public void setPermissions(List<Permission> permissions) {
        this.permissions = new HashSet<Permission>(permissions);
    }
     */

    public boolean addPermission(Permission perm){
        return this.permissions.add(perm);
    }

    public boolean addUser(Users u)
    {
        return users.add(u);
    }

    /* metawidget cannot deal with Set Collection
    public Set<Users> getUsers() {
        return users;
    }
     */

    public Set<Users> getUsers()
    {
        return users;
    }

    public void setUsers(Set<Users> users) {
        removeUsers();
        // perfs: could retro set user/group manually for better performances
        for (Users user: users)
        {
            addUser(user);
        }
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

    public Serializable getPrimaryKey()
    {
        return groupId;
    }

    private void removeUsers()
    {
        this.users.clear();
    }

    public void removeUser(Users u1)
    {
        HashSet<Users> newUsers = new HashSet<Users>();
        for (Users user: users)
        {
            if(!u1.equals(user)) newUsers.add(user);
        }
        this.users.clear();
        this.users = newUsers;
    }

    public void updateUsers(Set<Users> newUsers)
    {
        for (Users user: newUsers)
        {
            removeUser(user);
        }
    }

    private void removePermissions()
    {
        this.permissions.clear();
    }

    public void removePermission(Permission p1)
    {
        HashSet<Permission> newPermissions = new HashSet<Permission>();
        for (Permission permission: permissions)
        {
            if(!p1.equals(permission)) newPermissions.add(permission);
        }

        this.permissions = newPermissions;
    }

}
