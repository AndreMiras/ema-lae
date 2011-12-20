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

    public boolean addPermission(Permission perm){
        return this.permissions.add(perm);
    }

    public boolean addUser(Users user){
        return this.users.add(user);
    }

    public Set<Users> getUsers()
    {
        return users;
    }

    public void setUsers(Set<Users> users) {
        // it should be enough since the owner of the m2m relation is UserGroup
        this.users.clear();

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

    public boolean removeUser(Users u1)
    {
       boolean removed = false;
        // if doesn't come from database record
        if (u1.getUserId() == null)
        {
            removed = this.users.remove(u1);
        }
        else
        {
            boolean foundUser = false;
            Users tmpUser = null;
            Iterator<Users> it = this.users.iterator();
            while(!foundUser && it.hasNext())
            {
                tmpUser = it.next();
                foundUser = u1.getUserId().equals(
                        tmpUser.getUserId());
            }
            if (foundUser)
            {
                removed = this.users.remove(tmpUser);
            }
        }

        return removed;
    }

    private void removePermissions()
    {
        this.permissions.clear();
    }

    public boolean removePermission(Permission p1)
    {
        boolean removed = false;
        // if doesn't come from database record
        if (p1.getPermissionId() == null)
        {
            removed = this.permissions.remove(p1);
        }
        else
        {
            boolean foundUser = false;
            Permission tmpPermission = null;
            Iterator<Permission> it = this.permissions.iterator();
            while(!foundUser && it.hasNext())
            {
                tmpPermission = it.next();
                foundUser = p1.getPermissionId().equals(
                        tmpPermission.getPermissionId());
            }
            if (foundUser)
            {
                removed = this.permissions.remove(tmpPermission);
            }
        }

        return removed;
    }

}
