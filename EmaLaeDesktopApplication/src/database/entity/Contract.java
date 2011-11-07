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
    private int ID;
    @Column(name ="apprenticeID")
    private int IDApprentice;
    @Column(name ="internshipSupervisorID")
    private int IDInternshipSupervisor;
    @Column(name ="supervisingTeacherID")
    private int IDSupervisingTeacher;
    @Column(name ="beginDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date beginDate;
    @Column(name ="endDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDApprentice() {
        return IDApprentice;
    }

    public int getIDInternshipSupervisor() {
        return IDInternshipSupervisor;
    }

    public int getIDSupervisingTeacher() {
        return IDSupervisingTeacher;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setIDApprentice(int IDApprentice) {
        this.IDApprentice = IDApprentice;
    }

    public void setIDInternshipSupervisor(int IDInternshipSupervisor) {
        this.IDInternshipSupervisor = IDInternshipSupervisor;
    }

    public void setIDSupervisingTeacher(int IDSupervisingTeacher) {
        this.IDSupervisingTeacher = IDSupervisingTeacher;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
}
