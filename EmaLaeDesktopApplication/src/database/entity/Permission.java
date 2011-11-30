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
@Table(name = "permissions")
public class Permission implements Serializable{

    @Column(name ="permissionsID")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer permissionID;
    @Column(name ="name")
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="permissions")
    private Set<Group> groups;

    public Permission() {
        this.groups = new HashSet<Group>();
    }

    public Permission(String permissionName){
        this();
        this.name = permissionName;
    }

    /*
    public Set<Group> getGroups() {
        return this.groups;
    }
     */

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

     // meta-widget better deals with Lists at the moment
    public List<Group> getGroups() {
        List<Group> groupList = new ArrayList<Group>(groups);
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

    public Integer getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Integer permissionID) {
        this.permissionID = permissionID;
    }

    public boolean addToGroup(Group group){
        return this.groups.add(group);
    }

    @Override
    public String toString()
    {
        return name;
    }

    public boolean containsGroup(Group group){
        Iterator<Group> it = this.getGroups().iterator();
        boolean foundGroup = false;
        while(!foundGroup && it.hasNext()){
            foundGroup = group.getGroupId().equals(it.next().getGroupId());
        }
        return foundGroup;
    }
}
