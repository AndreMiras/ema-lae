/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Parameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author pc
 */
@Entity
public class UserProfile implements Serializable {

    //TODO: check this code
    @GenericGenerator(name = "generator", strategy = "foreign",
    parameters = @Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Users user;
    @Column(name ="firstName")
    private String firstName;
    @Column(name ="lastName")
    private String lastName;
    @Column(name ="birthDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;
    @Column(name ="address")
    private String address;
    @Column(name ="phone")
    private String phoneNumber;
    @Column(name ="email")
    private String email;
    public enum Type { Apprentice, InternshipSupervisor, SupervisingTeacher }
    @Column(name="type")
    private Type userProfileType;

    public UserProfile()
    {
    }

    public UserProfile(Users user)
    {
        this.user = user;
    }

    public UserProfile(Users user, Type userProfileType) {
        this.user = user;
        this.userProfileType = userProfileType;
    }

    
    public Integer getUserId()
    {
        return userId;
    }

    public Users getUser()
    {
        return user;
    }


    public String getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUser(Users user)
    {
        this.user = user;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString()
    {
        return getFullName();
    }

    public Type getUserProfileType() {
        return userProfileType;
    }

    public void setUserProfileType(Type userProfileType) {
        this.userProfileType = userProfileType;
    }

    public boolean isApprentice(){
        return this.getUserProfileType().equals(Type.Apprentice);
    }

    public boolean isInternshipSupervisor(){
        return this.getUserProfileType().equals(Type.InternshipSupervisor);
    }

    public boolean isSupervisingTeacher(){
        return this.getUserProfileType().equals(Type.SupervisingTeacher);
    }
    
}
