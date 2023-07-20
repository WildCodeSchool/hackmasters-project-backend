package com.templateproject.api.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jdk.jfr.Name;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

//    @NotBlank(message = "Username is mandatory")
//    @Size(min = 2, message = "Username must be at least 2 characters long")
    @Column (name = "username")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email
    @Column(unique=true, nullable=false)
    private String email;


    @Column( name = "firstname")
    private String firstname;

    //EAGER, will directly fetch the roles, longer loading time
    //Mandatory for security users and roles management
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities = new HashSet<Role>();

    public User() {}

    public User(String password, String email, String firstname, Set<Role> roles) {
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.authorities = roles;
    }



    public Long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    //Not really natural but we use the email as the username
    @Override
    public String getUsername() {
        return this.email;
    }



    public String getFirstname() {
        return firstname;
    }

    public String getUserStringName() {
        return this.username;
    }




    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}