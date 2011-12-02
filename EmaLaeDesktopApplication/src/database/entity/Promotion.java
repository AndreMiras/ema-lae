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
public class Promotion implements Serializable{

    @Column(name ="promotions_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private int promotionId;
    @Column
    private String name;
    @Column
    private int promotionYear;
    @Column
    private int responsibleId;
    @Column
    private int adminId;
    
    private Set<Integer> IDApprentice;

    public int getID() {
        return promotionId;
    }

    public void setID(int ID) {
        this.promotionId = ID;
    }

    public int getIDAdmin() {
        return adminId;
    }

    public void setIDAdmin(int IDAdmin) {
        this.adminId = IDAdmin;
    }

    public Set<Integer> getIDApprentice() {
        return IDApprentice;
    }

    public void setIDApprentice(Set<Integer> IDApprentice) {
        this.IDApprentice = IDApprentice;
    }

    public int getIDresponsible() {
        return responsibleId;
    }

    public void setIDresponsible(int IDresponsible) {
        this.responsibleId = IDresponsible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return promotionYear;
    }

    public void setYear(int year) {
        this.promotionYear = year;
    }

    
}
