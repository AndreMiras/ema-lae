/*
 * TODO:
 *      - add email to the model
 */

package database.entity;

import java.io.Serializable;
import javax.persistence.*;

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
    //@Column(name="groupID")
    private int groupID;

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public User()
    {
    }

    public User(String username)
    {
        this.username = username;

        // TODO: set random password when not specified
        this.password = "";
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

    
}
