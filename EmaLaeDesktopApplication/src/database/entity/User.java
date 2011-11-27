/*
 * TODO:
 *      - add email to the model
 */

package database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Column(name ="user_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer userId;
    @Column(name ="username")
    private String username;
    @Column(name ="password")
    private String password;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Group> groups;

    public User()
    {
        this.groups = new HashSet<Group>();
    }

    public User(String username)
    {
        this.username = username;

        // TODO: set random password when not specified
        this.password = "";

        this.groups = new HashSet<Group>();
    }

    // meta-widget better deals with List Collections at the moment
    public List<Group> getGroups() {
        List<Group> groupList = new ArrayList<Group>(groups);
        return groupList;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public boolean addToGroup(Group newGroup){
        return this.groups.add(newGroup);
    }


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return username;
    }

    public boolean checkPermission (Permission permission){
        boolean hasPermission = false;
        Iterator i=this.groups.iterator();
        while(i.hasNext() && !hasPermission)
        {
            Group userGroup = (Group) i.next();
            hasPermission = userGroup.getPermissionsID().contains(permission) ? true : hasPermission;
        }
        return hasPermission;
    }
    
}
