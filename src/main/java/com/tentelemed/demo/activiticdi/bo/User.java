package com.tentelemed.demo.activiticdi.bo;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 19/04/13
 * Time: 15:08
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    Integer id;

    @Version
    protected Integer optlock;

    String login;
    String password;
    String firstName;
    String lastName;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Transient
    public String getFullName() {
        return getFirstName()+" "+getLastName();
    }
}
