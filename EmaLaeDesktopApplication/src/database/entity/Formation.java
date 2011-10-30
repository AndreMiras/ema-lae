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
public class Formation {

    private int ID;
    private int parentID;
    private List<Integer> childrenID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Integer> getChildrenID() {
        return childrenID;
    }

    public void setChildrenID(List<Integer> childrenID) {
        this.childrenID = childrenID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
    
    

}
