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
public class Promotion {

    private int ID;
    private String name;
    private int year;
    private int IDresponsible;
    private int IDAdmin;
    private List<Integer> IDApprentice;

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

    public List<Integer> getIDApprentice() {
        return IDApprentice;
    }

    public void setIDApprentice(List<Integer> IDApprentice) {
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
