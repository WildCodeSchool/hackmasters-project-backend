    package com.templateproject.api.entity;

    import java.util.Collection;
    import java.util.HashSet;
    import java.util.Set;
    import org.springframework.security.core.GrantedAuthority;
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
    import org.springframework.security.core.userdetails.UserDetails;

    @Entity
    @Table(name = "users")
    public class Users implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "users_id")
        private Long id;

        @Column(name = "usersname")
        private String usersname;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;

        @Email
        @Column(unique = true, nullable = false)
        private String email;

        @Column(name = "firstname")
        private String firstname;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "users_role_junction",
                joinColumns = @JoinColumn(name = "users_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
        )
        private Set<Role> authorities = new HashSet<>();

        public Users() {
        }

        public Users(String password, String email, String firstname, Set<Role> roles) {
            this.password = password;
            this.email = email;
            this.firstname = firstname;
            this.authorities = roles;
        }

        public Long getId() {
            return this.id;
        }

        public void setUsersname(String usersname) {
            this.usersname = usersname;
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

        @Override
        public String getUsername() {
            return this.email;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getUserStringName() { // Renamed 'getuserstringName' to 'getUserStringName'
            return this.usersname; // Changed to use 'username' instead of 'usersname'
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
