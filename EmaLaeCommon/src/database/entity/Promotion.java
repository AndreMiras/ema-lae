/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import javax.persistence.*;
import java.util.Set;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class Promotion implements Serializable, WithPrimaryKey {

    @Column(name ="promotion_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer promotionId;
    @Column
    private String name;
    @Column
    private Integer promotionYear;
    @OneToOne
    private UserProfile responsible;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotion")
    private Set<UserProfile> apprentices;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotion")
    private Set<Formation> formations;

    public Promotion()
    {
        this.apprentices = new HashSet<UserProfile>();
        this.formations = new HashSet<Formation>();
    }

    public Promotion(String name)
    {
        this();
        this.name = name;
    }


    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Set<UserProfile> getApprentices() {
        return apprentices;
    }

    public void setApprentices(Set<UserProfile> apprentices) {
        this.apprentices = apprentices;
    }

    public UserProfile getResponsible() {
        return responsible;
    }

    public void setResponsible(UserProfile responsible) {
        this.responsible = responsible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPromotionYear() {
        return promotionYear;
    }

    public void setPromotionYear(Integer promotionYear)
    {
        this.promotionYear = promotionYear;
    }

    public Set<Formation> getFormations() {
        return formations;
    }

    public void setFormations(Set<Formation> formations) {
        this.formations = formations;
        for (Formation formation: formations)
        {
            formation.setPromotion(this);
        }
    }

    public boolean addFormation(Formation formation)
    {
        if (!formation.getPromotion().equals(this))
        {
            formation.setPromotion(this);
        }
        return this.formations.add(formation);
    }

    @Override
    public String toString()
    {
        return name + " (" + promotionYear + ")";
    }

    public Serializable getPrimaryKey()
    {
        return promotionId;
    }

    public boolean containsFormation(Formation formation) {
        Formation tmpFormation;
        Iterator<Formation> it = this.getFormations().iterator();
        boolean foundFormation = false;
        while(!foundFormation && it.hasNext())
        {
            tmpFormation = it.next();
            if (formation.getFormationId() != null)
            {
                foundFormation = formation.getFormationId().equals(
                        tmpFormation.getFormationId());
            }
            else
                foundFormation = formation.getName().equals(tmpFormation.getName());
        }
        return foundFormation;
    }
}