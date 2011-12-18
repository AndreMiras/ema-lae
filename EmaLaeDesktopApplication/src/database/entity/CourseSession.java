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
    private Integer sessionId;
    @Temporal(TemporalType.DATE)
    @Column
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column
    private Date endDate;
    @Column
    private String assignmentsLink;
    public enum SessionType { Course, Test, Pratictal }
    @Column
    private SessionType type;
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn
    private Formation formation;

    public CourseSession()
    {
    }

    public CourseSession(Formation formation)
    {
        this.formation = formation;
    }

    public CourseSession(SessionType type)
    {
        this.type = type;
    }

    public CourseSession(SessionType type, Formation formation)
    {
        this.type = type;
        this.formation = formation;
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

    public SessionType getType() {
        return type;
    }

    public void setSessionType(SessionType sessionType) {
        this.type = sessionType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Formation getFormation()
    {
        if (formation == null)
            return null;
        else
            return formation;
    }

    public void setFormation(Formation formation)
    {
        this.formation = formation;
        if (formation != null && !formation.containsSession(this))
        {
            formation.addCourseSession(this);
        }
    }

    public Integer getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(Integer sessionId)
    {
        this.sessionId = sessionId;
    }

    public Serializable getPrimaryKey()
    {
        return sessionId;
    }
    

}
