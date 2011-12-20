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
public class ApprenticeSession implements Serializable, WithPrimaryKey {

    @Column(name ="apprentice_session_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private int ID;
    private int apprenticeId;
    @Column(name ="session_id")
    private int sessionId;
    @Column
    private float mark;
    @Column
    private String documentLink;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDApprentice() {
        return apprenticeId;
    }

    public int getIDSession() {
        return sessionId;
    }

    public String getDocumentLink() {
        return documentLink;
    }

    public float getMark() {
        return mark;
    }

    public void setIDApprentice(int IDApprentice) {
        this.apprenticeId = IDApprentice;
    }

    public void setIDSession(int IDSession) {
        this.sessionId = IDSession;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public Serializable getPrimaryKey()
    {
        return ID;
    }

}
