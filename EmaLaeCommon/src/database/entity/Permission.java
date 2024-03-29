/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO: Generate tests for users and permission : test a viewprofile on an authorized user, and on an unauthorized  user.
// TODO: content type reference or at least ref to Class type

package database.entity;

import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import java.util.Iterator;


@Entity
public class Permission implements Serializable, WithPrimaryKey {

    @Column(name ="permissions_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer permissionId;
    @Column
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="permissions")
    private Set<UserGroup> groups;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Users> users;

    public Permission() {
        this.groups = new HashSet<UserGroup>();
        this.users = new HashSet<Users>();
    }

    public Permission(String permissionName){
        this();
        this.name = permissionName;
    }


    public void setGroups(Set<UserGroup> groups) {
        this.groups.clear();
        // perfs: could retro set user/group manually for better performances
        for (UserGroup group: groups)
        {
            addToGroup(group);
        }
    }

    public Set<UserGroup> getGroups() {
        return groups;
    }

    

//    @Column(name ="contentType")
//    private int contentType;
//
//    public int getContentType() {
//        return contentType;
//    }
//
//    public void setContentType(int contentType) {
//        this.contentType = contentType;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionID) {
        this.permissionId = permissionID;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    public void addUsers(Users user){
        this.users.add(user);
    }

    public boolean addToGroup(UserGroup group){
        group.addPermission(this); // workaround, seems required by mappedBy
        return this.groups.add(group);
    }

    public boolean removeFromGroup(UserGroup group){
        group.addPermission(this); // workaround, seems required by mappedBy
        return this.groups.add(group);
    }

    @Override
    public String toString()
    {
        return name;
    }

    public boolean containsGroup(UserGroup group){
        Iterator<UserGroup> it = this.getGroups().iterator();
        boolean foundGroup = false;
        while(!foundGroup && it.hasNext()){
            foundGroup = group.getGroupId().equals(it.next().getGroupId());
        }
        return foundGroup;
    }

    public Serializable getPrimaryKey()
    {
        return permissionId;
    }

}
