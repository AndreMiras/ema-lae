/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author pc
 */

@Entity
public class ApprenticeSession implements Serializable{

    @Column(name ="apprenticesSessionId")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private int ID;
    @Column(name ="apprenticeID")
    private int IDApprentice;
    @Column(name ="sessionID")
    private int IDSession;
    @Column(name ="mark")
    private float mark;
    @Column(name ="documentLink")
    private String documentLink;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDApprentice() {
        return IDApprentice;
    }

    public int getIDSession() {
        return IDSession;
    }

    public String getDocumentLink() {
        return documentLink;
    }

    public float getMark() {
        return mark;
    }

    public void setIDApprentice(int IDApprentice) {
        this.IDApprentice = IDApprentice;
    }

    public void setIDSession(int IDSession) {
        this.IDSession = IDSession;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

}
