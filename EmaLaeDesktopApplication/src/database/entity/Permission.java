/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO: Generate tests for users and permission : test a viewprofile on an authorized user, and on an unauthorized  user.

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Group> groups;

    public Permission() {
        this.groups = new HashSet<Group>();
    }

    public Permission(String permissionName){
        this.name = permissionName;
        this.groups = new HashSet<Group>();
    }

    public Set<Group> getGroupsID() {
        return this.groups;
    }

    public void setGroupsID(Set<Group> groups) {
        this.groups = groups;
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

    public boolean addGroup(Group group){
        return this.groups.add(group);
    }

    

}
