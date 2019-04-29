package com.ricko.passwordmanager.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String login;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Data> data;

    public List<Data> getData(){
        return data;
    }

    /*@OneToMany
    @JoinColumn(name="userId")
    List<Data> data=new ArrayList<>();*/


    /*The default constructor only exists for the sake of JPA. You won’t use it directly, so it is designated as protected.*/
    protected User() {
    }

    /*The other constructor is the one you’ll use to create instances of Customer to be saved to the database.*/
    public User(String name, String surname, String email, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

/*
@Entity: Specifies that the class is an entity. This annotation is applied to the entity class.
@Id: Specifies the primary key of an entity.
@GeneratedValue: Provides for the specification of generation strategies for the values of primary keys.
public Student(): Default constructor to make JPA Happy*/
