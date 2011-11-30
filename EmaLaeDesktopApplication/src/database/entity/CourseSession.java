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
public class CourseSession implements Serializable{

    @Column(name ="sessionsID")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private int ID;
    @Temporal(TemporalType.DATE)
    @Column(name ="startDate")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name ="endDate")
    private Date endDate;
    @Column(name ="assignments")
    private String assignmentsLink;
    @Column(name ="type")
    private int sessionType;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAssignmentsLink() {
        return assignmentsLink;
    }

    public void setAssignmentsLink(String assignmentsLink) {
        this.assignmentsLink = assignmentsLink;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSessionType() {
        return sessionType;
    }

    public void setSessionType(int sessionType) {
        this.sessionType = sessionType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    

}
