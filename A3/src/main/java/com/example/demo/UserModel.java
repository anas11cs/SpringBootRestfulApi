/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
/**
 *
 * @author DELL
 */
@Entity
@Table(name="Users")
public class UserModel implements Serializable{

    String name;
    @Id
    String email;
    String password;
    String gender;
    Integer dobday;
    Integer dobmonth;
    Integer dobyear;

    public UserModel() {
                this.name = null;
        this.email = null;
        this.password = null;
        this.gender = null;
        this.dobday = null;
        this.dobmonth = null;
        this.dobyear = null;
    }
    public UserModel(String name, String email, String password, String gender, int dobday, int dobmonth, int dobyear) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dobday = dobday;
        this.dobmonth = dobmonth;
        this.dobyear = dobyear;
    }
    public UserModel(UserModel u)
    {
        this.name = u.name;
        this.email = u.email;
        this.password = u.password;
        this.gender = u.gender;
        this.dobday = u.dobday;
        this.dobmonth = u.dobmonth;
        this.dobyear = u.dobyear;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public Integer getDobday() {
        return dobday;
    }

    public Integer getDobmonth() {
        return dobmonth;
    }

    public Integer getDobyear() {
        return dobyear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDobday(Integer dobday) {
        this.dobday = dobday;
    }

    public void setDobmonth(Integer dobmonth) {
        this.dobmonth = dobmonth;
    }

    public void setDobyear(Integer dobyear) {
        this.dobyear = dobyear;
    }
    
}
