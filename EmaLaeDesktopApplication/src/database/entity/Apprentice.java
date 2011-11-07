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
@Table(name = "users")
public class Apprentice extends UserProfile implements Serializable{
    private int promotionID;
    private Set<Integer> sessions;

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public Set<Integer> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Integer> sessions) {
        this.sessions = sessions;
    }

    


}
