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
@Table(name = "promotions")
public class Promotion implements Serializable{

    @Column(name ="promotionsID")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private int ID;
    @Column(name ="name")
    private String name;
    @Column(name ="promotionYear")
    private int year;
    @Column(name ="responsibleID")
    private int IDresponsible;
    @Column(name ="adminID")
    private int IDAdmin;
    private Set<Integer> IDApprentice;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDAdmin() {
        return IDAdmin;
    }

    public void setIDAdmin(int IDAdmin) {
        this.IDAdmin = IDAdmin;
    }

    public Set<Integer> getIDApprentice() {
        return IDApprentice;
    }

    public void setIDApprentice(Set<Integer> IDApprentice) {
        this.IDApprentice = IDApprentice;
    }

    public int getIDresponsible() {
        return IDresponsible;
    }

    public void setIDresponsible(int IDresponsible) {
        this.IDresponsible = IDresponsible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
}
