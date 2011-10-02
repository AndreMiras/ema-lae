/*
 * TODO:
 *      - add email to the model
 */

package database.entity;

import java.io.Serializable;

/**
 *
 * @author andre
 */
public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;

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
