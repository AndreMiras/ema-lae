/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


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
    @OneToMany(mappedBy = "promotion")
    private Set<UserProfile> apprentices;

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

    public int getYear() {
        return promotionYear;
    }

    public void setYear(int year) {
        this.promotionYear = year;
    }

    public Serializable getPrimaryKey()
    {
        return promotionId;
    }
}