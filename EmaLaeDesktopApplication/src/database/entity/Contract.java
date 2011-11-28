/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author pc
 */

@Entity
@Table(name = "contracts")
public class Contract implements Serializable{

    @Column(name ="contractsID")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer ID;
    @OneToOne()
    @JoinColumn(name ="apprentice")
    private UserProfile apprentice;
    @OneToOne()
    @JoinColumn(name ="internshipSupervisor")
    private UserProfile internshipSupervisor;
    @OneToOne()
    @JoinColumn(name ="supervisingTeacher")
    private UserProfile supervisingTeacher;
    @Column(name ="beginDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date beginDate;
    @Column(name ="endDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    public Contract() {
    }

    public Contract(UserProfile IDApprentice, UserProfile IDInternshipSupervisor, UserProfile IDSupervisingTeacher) {
        this.apprentice = IDApprentice;
        this.internshipSupervisor = IDInternshipSupervisor;
        this.supervisingTeacher = IDSupervisingTeacher;
    }

    private Contract(UserProfile IDApprentice, UserProfile IDInternshipSupervisor, UserProfile IDSupervisingTeacher, Date beginDate, Date endDate) {
        this.apprentice = IDApprentice;
        this.internshipSupervisor = IDInternshipSupervisor;
        this.supervisingTeacher = IDSupervisingTeacher;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

//    Contract newContract(UserProfile apprentice, UserProfile internshipSupervisor, UserProfile supervisingTeacher){

  //  }

   
    public Integer getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public UserProfile getApprentice() {
        return apprentice;
    }

    public void setApprentice(UserProfile apprentice) {
        this.apprentice = apprentice;
    }

    public UserProfile getInternshipSupervisor() {
        return internshipSupervisor;
    }

    public void setInternshipSupervisor(UserProfile internshipSupervisor) {
        this.internshipSupervisor = internshipSupervisor;
    }

    public UserProfile getSupervisingTeacher() {
        return supervisingTeacher;
    }

    public void setSupervisingTeacher(UserProfile supervisingTeacher) {
        this.supervisingTeacher = supervisingTeacher;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
}
