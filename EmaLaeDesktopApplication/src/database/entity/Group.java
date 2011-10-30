/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.util.List;

/**
 *
 * @author pc
 */
public class Group {

    private int ID;
    private String name;
    private List<Integer> usersId;
    private List<Integer> permissionsID;

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

    public List<Integer> getPermissionsID() {
        return permissionsID;
    }

    public void setPermissionsID(List<Integer> permissionsID) {
        this.permissionsID = permissionsID;
    }

    public List<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Integer> usersId) {
        this.usersId = usersId;
    }

    

}
