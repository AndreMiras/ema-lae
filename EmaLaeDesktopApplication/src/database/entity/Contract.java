/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import exceptions.ContractException;

/**
 *
 * @author pc
 */



@Entity
public class Contract implements Serializable{

    @Column(name ="contracts_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer ID;
    @OneToOne()
    @JoinColumn
    private UserProfile apprentice;
    @OneToOne()
    @JoinColumn
    private UserProfile internshipSupervisor;
    @OneToOne()
    @JoinColumn
    private UserProfile supervisingTeacher;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date beginDate;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    public Contract() {
    }

    public Contract(UserProfile IDApprentice, UserProfile IDInternshipSupervisor, UserProfile IDSupervisingTeacher) throws ContractException {
        if(IDApprentice.getUserProfileType() == UserProfile.Type.Apprentice
                && IDInternshipSupervisor.getUserProfileType() == UserProfile.Type.InternshipSupervisor
                && IDSupervisingTeacher.getUserProfileType() == UserProfile.Type.SupervisingTeacher){
            this.apprentice = IDApprentice;
            this.internshipSupervisor = IDInternshipSupervisor;
            this.supervisingTeacher = IDSupervisingTeacher;
        }
        else{
            throw new ContractException();
        }
    }

    public Contract(UserProfile IDApprentice, UserProfile IDInternshipSupervisor, UserProfile IDSupervisingTeacher, Date beginDate, Date endDate) throws ContractException {
        this(IDApprentice, IDInternshipSupervisor, IDSupervisingTeacher);
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

    /**
     * Verifies and sets the apprentice if the type is correct, otherwise throws
     * an exception
     * @param apprentice
     * @throws ContractException
     */
    public void setApprentice(UserProfile apprentice) throws ContractException{
        if(apprentice.getUserProfileType() == UserProfile.Type.Apprentice)this.apprentice = apprentice;
        else throw new ContractException();
    }

    public UserProfile getInternshipSupervisor() {
        return internshipSupervisor;
    }

    /**
     * Verifies and sets the internship supervisor if the type is correct,
     * otherwise throws an exception
     * @param internshipSupervisor
     * @throws ContractException
     */
    public void setInternshipSupervisor(UserProfile internshipSupervisor) throws ContractException{
        if (internshipSupervisor.getUserProfileType() == UserProfile.Type.InternshipSupervisor)this.internshipSupervisor = internshipSupervisor;
        else throw new ContractException();
    }

    public UserProfile getSupervisingTeacher() {
        return supervisingTeacher;
    }

    /**
     * Verifies and sets the supervising teach if the type is correct,
     * otherwise throws an exception
     * @param supervisingTeacher
     * @throws ContractException
     */
    public void setSupervisingTeacher(UserProfile supervisingTeacher) throws ContractException{
        if (supervisingTeacher.getUserProfileType() == UserProfile.Type.SupervisingTeacher)this.supervisingTeacher = supervisingTeacher;
        else throw new ContractException();
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

    public boolean isCorrect(){
        return this.apprentice.isApprentice()
                && this.internshipSupervisor.isInternshipSupervisor()
                && this.supervisingTeacher.isSupervisingTeacher();
    }

    
}
