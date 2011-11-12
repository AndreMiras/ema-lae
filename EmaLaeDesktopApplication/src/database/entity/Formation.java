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
@Table(name = "formations")
public class Formation implements Serializable{

    @Column(name ="formationsID")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private int ID;
    @Column(name ="parentID")
    private int parentID;
    private Set<Integer> childrenID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Set<Integer> getChildrenID() {
        return childrenID;
    }

    public void setChildrenID(Set<Integer> childrenID) {
        this.childrenID = childrenID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
    
    

}
