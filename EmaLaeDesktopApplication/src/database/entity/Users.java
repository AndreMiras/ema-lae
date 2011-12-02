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
public class Users implements Serializable {

    @Column(name="user_id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true)
    private String username;
    @Column
    private String password;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="users")
    private Set<UserGroup> groups;

    public Users()
    {
        this.groups = new HashSet<UserGroup>();
    }

    public Users(String username)
    {
        this();
        this.username = username;

        // TODO: set random password when not specified
        this.password = "";
    }

    // meta-widget better deals with List Collections at the moment
    public List<UserGroup> getGroups() {
        List<UserGroup> groupList = new ArrayList<UserGroup>(groups);
        return groupList;
    }

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }

    public boolean addToGroup(UserGroup newGroup){
        newGroup.addUser(this); // workaround, seems required by mappedBy
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
            UserGroup userGroup = (UserGroup) i.next();
            hasPermission = userGroup.getPermissions().contains(permission) ? true : hasPermission;
        }
        return hasPermission;
    }

    public boolean containsGroup(UserGroup group){
        Iterator<UserGroup> it = this.groups.iterator();
        boolean foundGroup = false;
        while(!foundGroup && it.hasNext()){
            foundGroup = group.getGroupId().equals(it.next().getGroupId());
        }
        return foundGroup;
    }
    
}
