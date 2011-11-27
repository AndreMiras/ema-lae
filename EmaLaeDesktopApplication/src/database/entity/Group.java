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
    private Set<User> usersId;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "permission_group", joinColumns =
        {@JoinColumn(name = "groupsID") },
        inverseJoinColumns = { @JoinColumn(name = "permissionsID") }
    )
    private Set<Permission> permissionsID;

    public Group()
    {
        this.usersId = new HashSet<User>();
        this.permissionsID = new HashSet<Permission>();
    }


    public Group(String groupName){
       this.name = groupName;
       this.usersId = new HashSet<User>();
       this.permissionsID = new HashSet<Permission>();
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
    public Set<Permission> getPermissionsID() {
        return permissionsID;
    }

    public void setPermissionsID(Set<Permission> permissionsID) {
        this.permissionsID = permissionsID;
    }
     */

    // meta-widget better deals with Lists at the moment
    public List<Permission> getPermissionsID()
    {
        List<Permission> permissionList =
                new ArrayList<Permission>(permissionsID);
        return permissionList;
    }

    public void setPermissionsID(List<Permission> permissionsID) {
        this.permissionsID = new HashSet<Permission>(permissionsID);
    }

    public boolean addPermission(Permission perm){
        return this.permissionsID.add(perm);
    }

    public boolean addUser(User u)
    {
        return usersId.add(u);
    }

    public Set<User> getUsersId() {
        return usersId;
    }

    public void setUsersId(Set<User> usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString()
    {
        return name;
    }

}
