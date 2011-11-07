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
    private int ID;
    @Column(name ="name")
    private String name;
    private Set<Integer> usersId;
    private Set<Integer> permissionsID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getPermissionsID() {
        return permissionsID;
    }

    public void setPermissionsID(Set<Integer> permissionsID) {
        this.permissionsID = permissionsID;
    }

    public Set<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(Set<Integer> usersId) {
        this.usersId = usersId;
    }

    

}
