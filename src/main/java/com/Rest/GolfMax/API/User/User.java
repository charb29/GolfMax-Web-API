package com.Rest.GolfMax.API.User;

import com.Rest.GolfMax.API.PlayerStatistics.PlayerStatistics;
import com.Rest.GolfMax.API.Score.Score;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    
    @OneToMany(mappedBy = "user")
    private List<Score> scores;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlayerStatistics playerStatistics;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, unique = false)
    private String password;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public User() {}

    public User (long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

   public void setId(long id) {
        this.id = id;
   }

   public String getUsername() {
        return username;
   }

   public void setUsername(String username) {
        this.username = username;
   }

   public String getPassword() {
        return password;
   }

   public void setPassword(String password) {
        this.password = password;
   }

   public String getEmail() {
        return email;
   }

   public void setEmail(String email) {
        this.email = email;
   }
}
