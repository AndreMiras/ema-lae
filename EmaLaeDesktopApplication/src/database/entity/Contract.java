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
public class Contract {

    private int ID;
    private int IDApprentice;
    private int IDInternshipSupervisor;
    private int IDSupervisingTeacher;
    private Date beginDate;
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
