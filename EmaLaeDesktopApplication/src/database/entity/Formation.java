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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Formation parentFormation;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentFormation")
    // @JoinColumn
    private Set<Formation> childrenFormations = new HashSet<Formation>();
    @ManyToOne(cascade = CascadeType.ALL)
    private Promotion promotion;

    public Formation() {

    }

    public Formation(String name) {
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
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
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
}