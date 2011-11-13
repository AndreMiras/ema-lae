/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import database.entity.Apprentice;
import database.entity.InternshipSupervisor;
import database.entity.SupervisingTeacher;

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
    @Column(name ="apprenticeID")
    private Apprentice apprentice;
    @Column(name ="internshipSupervisorID")
    private InternshipSupervisor internshipSupervisor;
    @Column(name ="supervisingTeacherID")
    private SupervisingTeacher supervisingTeacher;
    @Column(name ="beginDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date beginDate;
    @Column(name ="endDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    public Contract() {
    }

    public Contract(Apprentice IDApprentice, InternshipSupervisor IDInternshipSupervisor, SupervisingTeacher IDSupervisingTeacher) {
        this.apprentice = IDApprentice;
        this.internshipSupervisor = IDInternshipSupervisor;
        this.supervisingTeacher = IDSupervisingTeacher;
    }

    public Contract(Apprentice IDApprentice, InternshipSupervisor IDInternshipSupervisor, SupervisingTeacher IDSupervisingTeacher, Date beginDate, Date endDate) {
        this.apprentice = IDApprentice;
        this.internshipSupervisor = IDInternshipSupervisor;
        this.supervisingTeacher = IDSupervisingTeacher;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

   
    public Integer getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Apprentice getApprentice() {
        return apprentice;
    }

    public void setApprentice(Apprentice apprentice) {
        this.apprentice = apprentice;
    }

    public InternshipSupervisor getInternshipSupervisor() {
        return internshipSupervisor;
    }

    public void setInternshipSupervisor(InternshipSupervisor internshipSupervisor) {
        this.internshipSupervisor = internshipSupervisor;
    }

    public SupervisingTeacher getSupervisingTeacher() {
        return supervisingTeacher;
    }

    public void setSupervisingTeacher(SupervisingTeacher supervisingTeacher) {
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
