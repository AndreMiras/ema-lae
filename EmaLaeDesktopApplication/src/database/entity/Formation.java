/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author pc
 */

@Entity
@Table(name = "formations")
public class Formation implements Serializable{

    @Column(name ="formationsID")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer ID;
    @Column(name="formationsName")
    private String name;
    @ManyToOne
    @JoinColumn(name = "parentFormation")
    private Formation parentFormation;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="childrenFormations")
    private Set<Formation> childrenFormations = new HashSet<Formation>();

    public Formation() {

    }

    public Formation(Formation parentFormation) {
        this.parentFormation = parentFormation;
    }

    public Formation(String name) {
        this.name = name;
    }

    public Formation(Formation parentFormation, String name) {
        this.name = name;
        this.parentFormation = parentFormation;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

        
    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Set<Formation> getChildrenFormations() {
        return childrenFormations;
    }

    public void setChildrenFormations(Set<Formation> childrenFormations) {
        this.childrenFormations = childrenFormations;
    }

    public Formation getParentFormation() {
        return parentFormation;
    }

    public void setParentFormation(Formation parentFormation) {
        this.parentFormation = parentFormation;
    }
    
    public boolean addFormation(Formation child){
        return this.childrenFormations.add(child);
    }

}
