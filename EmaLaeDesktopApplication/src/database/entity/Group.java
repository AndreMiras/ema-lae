/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "user_group", joinColumns =
        {@JoinColumn(name = "groupsID") },
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> usersId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "permission_group", joinColumns =
        {@JoinColumn(name = "groupsID") },
        inverseJoinColumns = { @JoinColumn(name = "permissionsID") }
    )
    private Set<Permission> permissionsID;

    public Group() {
    }


    public Group(String groupName){
       this.name = groupName;
    }

    public Integer getID() {
        return this.groupId;
    }

    public void setID(Integer ID) {
        this.groupId = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissionsID() {
        return permissionsID;
    }

    public void setPermissionsID(Set<Permission> permissionsID) {
        this.permissionsID = permissionsID;
    }

    public Set<User> getUsersId() {
        return usersId;
    }

    public void setUsersId(Set<User> usersId) {
        this.usersId = usersId;
    }

    

}
