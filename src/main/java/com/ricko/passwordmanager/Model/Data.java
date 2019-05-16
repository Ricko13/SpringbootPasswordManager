package com.ricko.passwordmanager.Model;

import javax.persistence.*;

@Entity
public class Data {
    @Id
    @GeneratedValue
    private int id;
    private String website;
    private String password;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    /*public User getUser(){
        return user;
    }*/

    /*public Data(String website, String password, User user) {
        this.website = website;
        this.password = password;
        this.user=user;
    }*/
    public Data(String website, String password, User user) {
        this.website = website;
        this.password = password;
        this.user=user;
    }


    protected Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", website='" + website + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
