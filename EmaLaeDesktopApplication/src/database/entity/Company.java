/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author pc
 */

@Entity
public class Company implements Serializable{

    @Column(name ="company_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private int companyId;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String phoneNumber;

    public int getID() {
        return companyId;
    }

    public void setID(int ID) {
        this.companyId = ID;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    

}
