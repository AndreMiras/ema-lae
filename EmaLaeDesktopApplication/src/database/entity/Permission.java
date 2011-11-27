/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO: Generate tests for users and permission : test a viewprofile on an authorized user, and on an unauthorized  user.

package database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Set<Group> groups;

    public Permission() {
        this.groups = new HashSet<Group>();
    }

    public Permission(String permissionName){
        this.name = permissionName;
        this.groups = new HashSet<Group>();
    }

    /*
    public Set<Group> getGroupsID() {
        return this.groups;
    }

    public void setGroupsID(Set<Group> groups) {
        this.groups = groups;
    }
     */

     // meta-widget better deals with Lists at the moment
    public List<Group> getGroupsID() {
        List<Group> groupList = new ArrayList<Group>(groups);
        return groupList;
    }

    public void setGroupsID(Set<Group> groups) {
        this.groups = new HashSet<Group>(groups);
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

    

}
