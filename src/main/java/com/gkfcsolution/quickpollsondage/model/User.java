package com.gkfcsolution.quickpollsondage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gkfcsolution.quickpollsondage.util.BooleanToYesNoConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USERNAME")
    @NotEmpty
    private String username;

    @Column(name = "PASSWORD")
    @NotEmpty
    @JsonIgnore
    private String password;

    @Column(name = "FIRST_NAME")
    @NotEmpty
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotEmpty

    private String lastName;

    @Column(name = "EMAIL_NAME")
    @Nullable
    private String email;

    @Column(name = "PHONE")
    @Nullable
    private String phone;

    @Column(name = "ADMIN", columnDefinition = "char(3)")
//    @Type(type="yes_no")
    @Convert(converter = BooleanToYesNoConverter.class)
    private boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty String username) {
        this.username = username;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public @NotEmpty String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotEmpty
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(@NotEmpty boolean admin) {
        this.admin = admin;
    }
}
