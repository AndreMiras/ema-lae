/*
 * TODO:
 *      - add email to the model
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Group> groups;

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public boolean addGroup(Group newGroup){
        return this.groups.add(newGroup);
    }

    public User()
    {
    }

    public User(String username)
    {
        this.username = username;

        // TODO: set random password when not specified
        this.password = "";

        this.groups = new HashSet<Group>();
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
