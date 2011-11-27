/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
@Table(name = "groups")
public class Group implements Serializable{

    @Column(name ="groupsID")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer groupId;
    @Column(name ="name")
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "user_group", joinColumns =
        {@JoinColumn(name = "groupsID") },
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> users;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "permission_group", joinColumns =
        {@JoinColumn(name = "groupsID") },
        inverseJoinColumns = { @JoinColumn(name = "permissionsID") }
    )
    private Set<Permission> permissions;

    public Group()
    {
        this.users = new HashSet<User>();
        this.permissions = new HashSet<Permission>();
    }


    public Group(String groupName){
       this.name = groupName;
       this.users = new HashSet<User>();
       this.permissions = new HashSet<Permission>();
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

    public boolean addUser(User u)
    {
        return users.add(u);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return name;
    }

}
