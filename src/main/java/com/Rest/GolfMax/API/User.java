package com.Rest.GolfMax.API;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "email", nullable = false)
    private String email;

    public User() {
    }

    public User (long id, String username, String password, String email) 
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public long getId() 
    {
        return id;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
}
