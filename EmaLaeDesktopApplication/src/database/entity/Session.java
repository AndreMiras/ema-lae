/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;
import java.util.Date;

/**
 *
 * @author pc
 */
public class Session {

    private int ID;
    private Date startDate;
    private Date endDate;
    private String assignmentsLink;
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
