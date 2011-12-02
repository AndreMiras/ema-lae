/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO: Generate tests for users and permission : test a viewprofile on an authorized user, and on an unauthorized  user.

package database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import java.util.Iterator;

/**
 *
 * @author pc
 */
@Entity
public class Permission implements Serializable{

    @Column(name ="permissionsID")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer permissionId;
    @Column(name ="name")
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="permissions")
    private Set<UserGroup> groups;

    public Permission() {
        this.groups = new HashSet<UserGroup>();
    }

    public Permission(String permissionName){
        this();
        this.name = permissionName;
    }

    /*
    public Set<UserGroup> getGroups() {
        return this.groups;
    }
     */

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }

     // meta-widget better deals with Lists at the moment
    public List<UserGroup> getGroups() {
        List<UserGroup> groupList = new ArrayList<UserGroup>(groups);
        return groupList;
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

    public boolean addToGroup(UserGroup group){
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
}
