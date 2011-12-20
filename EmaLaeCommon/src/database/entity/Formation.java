/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class Formation implements Serializable, WithPrimaryKey {

    @Column(name = "formation_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer formationId;
    @Column
    private String name;

    @ManyToOne(cascade = {
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinColumn
    private Formation parentFormation;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentFormation")
    // @JoinColumn
    private Set<Formation> childrenFormations = new HashSet<Formation>();

    // it should be possible to do them all, except CascadeType.DELETE
    @ManyToOne(cascade = {
        CascadeType.MERGE,
        CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Promotion promotion;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "formation")
    private Set<CourseSession> sessions = new HashSet<CourseSession>();

    public Formation() {
    }

    public Formation(String name) {
        this();
        this.name = name;
    }

    /*
    public Formation(Formation parentFormation, String name) {
        this.name = name;
        this.setParentFormation(parentFormation);
    }
    */

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFormationId() {
        return this.formationId;
    }

    public void setFormationId(Integer ID) {
        this.formationId = ID;
    }

    public Set<Formation> getChildrenFormations() {
        if(childrenFormations == null)
            return null;
        else
            return childrenFormations;
    }

    public void setChildrenFormations(Set<Formation> childrenFormations) {
        this.childrenFormations = childrenFormations;
        for (Formation formation: childrenFormations)
        {
            formation.setParentFormation(this);
        }
    }

    public Formation getParentFormation() {
        if (parentFormation == null)
            return null;
        else
            return parentFormation;
    }

    public void setParentFormation(Formation parentFormation) {
        this.parentFormation = parentFormation;
        if (parentFormation != null && !parentFormation.containsChild(this))
        {
            parentFormation.addFormation(this);
        }
    }

    public Promotion getPromotion() {
        if (promotion == null)
            return new Promotion();
        else
            return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
        if (promotion != null && !promotion.containsFormation(this))
        {
            promotion.addFormation(this);
        }
    }
    
    public boolean addFormation(Formation child){
        if (!child.getParentFormation().equals(this))
        {
            child.setParentFormation(this);
        }
        return this.childrenFormations.add(child);
    }

    public boolean containsChild(Formation child){
        Formation tmpFormation;
        Iterator<Formation> it = this.getChildrenFormations().iterator();
        boolean foundFormation = false;
        while(!foundFormation && it.hasNext())
        {
            tmpFormation = it.next();
            if (child.getFormationId() != null)
            {
                foundFormation = child.getFormationId().equals(
                        tmpFormation.getFormationId());
            }
        }
        return foundFormation;
    }

    public Set<CourseSession> getSessions()
    {
        if (sessions == null)
            return null;
        else
            return sessions;
    }

    public void setSessions(Set<CourseSession> sessions)
    {
        this.sessions = sessions;
        for (CourseSession session: sessions)
        {
            session.setFormation(this);
        }
    }

    @Override
    public String toString()
    {
        String toStr = "";
        if (parentFormation != null)
        {
            toStr = parentFormation.toString() + "::";
        }
        toStr += name;

        return toStr;
    }

    public Serializable getPrimaryKey()
    {
        return formationId;
    }

    void removePromotion(Promotion promotion) {
        if(this.promotion.getPromotionId() != null && promotion.getPromotionId() != null)
        {
            if(this.promotion.getPromotionId().equals(promotion.getPromotionId()))
            {
                this.promotion = null;
            }
        }
        else if (this.promotion.getName().equals(promotion.getName()))
            this.promotion = null;

    }

    public boolean containsSession(CourseSession session)
    {
        CourseSession tmpSession;
        Iterator<CourseSession> it = this.getSessions().iterator();
        boolean foundSession = false;
        while(!foundSession && it.hasNext())
        {
            tmpSession = it.next();
            if (session.getSessionId() != null)
            {
                foundSession = session.getSessionId().equals(
                        tmpSession.getSessionId());
            }
        }
        return foundSession;
    }

    public boolean addCourseSession(CourseSession session)
    {
        if (!session.getFormation().equals(this))
        {
            session.setFormation(this);
        }
        return this.sessions.add(session);
    }
}