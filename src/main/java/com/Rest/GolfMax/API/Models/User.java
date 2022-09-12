package com.Rest.GolfMax.API.Models;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Score> scores;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlayerStatistics playerStatistics;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "verificationCode", length = 64)
    private String verificationCode;

    private boolean isEnabled;

    public User() {}

    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

   public void setId(Long id) {
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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
