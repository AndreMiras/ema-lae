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
public class CourseSession implements Serializable, WithPrimaryKey {

    @Column(name ="sessions_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private int sessionId;
    @Temporal(TemporalType.DATE)
    @Column
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column
    private Date endDate;
    @Column
    private String assignmentsLink;
    @Column
    private int sessionType;

    public int getID() {
        return sessionId;
    }

    public void setID(int ID) {
        this.sessionId = ID;
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

    public Serializable getPrimaryKey()
    {
        return sessionId;
    }
    

}
