package com.ricko.passwordmanager.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

//notes on the bottom

//class is annotated with @Entity, indicating that it is a JPA entity
@Entity
public class User {

    /*id property is annotated with @Id so that JPA will recognize it as the object’s ID. The id property
     is also annotated with @GeneratedValue to indicate that the ID should be generated automatically.*/
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String confirmationToken;
    private boolean enabled;
    @Transient
    private String passwordConfirm;
    //private String role;

    @OneToMany(mappedBy = "user")
    private List<Data> data;

    public List<Data> getData(){
        return data;
    }

    public void addData(Data data){
        this.data.add(data);
    }

    @ManyToMany
    private Set<Role> roles;

    /*@OneToMany
    @JoinColumn(name="userId")
    List<Data> data=new ArrayList<>();*/


    /*The default constructor only exists for the sake of JPA. You won’t use it directly, so it is designated as protected.*/
    public User() {
    }

    /*The other constructor is the one you’ll use to create instances of Customer to be saved to the database.*/
    public User(String name, String surname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setRoles(Set<Role> roles){
        this.roles=roles;
    }

    public Set<Role> getRoles(){
        return roles;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", login='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

/*
@Entity: Specifies that the class is an entity. This annotation is applied to the entity class.
@Id: Specifies the primary key of an entity.
@GeneratedValue: Provides for the specification of generation strategies for the values of primary keys.
public Student(): Default constructor to make JPA Happy*/
