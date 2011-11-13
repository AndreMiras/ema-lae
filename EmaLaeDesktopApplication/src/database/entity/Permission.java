/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO: Generate tests for users and permission : test a viewprofile on an authorized user, and on an unauthorized  user.

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Group> groups;

    public Permission() {
    }

    public Permission(String permissionName){
        this.name = permissionName;
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

    public void addGroup(Group group){
        this.groups.add(group);
    }

    

}
