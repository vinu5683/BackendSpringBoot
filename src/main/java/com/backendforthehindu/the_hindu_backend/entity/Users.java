package com.backendforthehindu.the_hindu_backend.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "dob")
    Date dob;

    @Column(name = "address")
    int address;

    @Column(name = "phone")
    int phone;

    @Column(name = "subscription")
    int subscription;

    @Column(name = "created_with_google")
    int created_with_google;

    public Users(String name, String email, String password, Date dob, int address, int phone, int subscription, int created_with_google) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.subscription = subscription;
        this.created_with_google = created_with_google;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getSubscription() {
        return subscription;
    }

    public void setSubscription(Integer subscription) {
        this.subscription = subscription;
    }

    public int getCreated_with_google() {
        return created_with_google;
    }

    public void setCreated_with_google(int created_with_google) {
        this.created_with_google = created_with_google;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", address=" + address +
                ", phone=" + phone +
                ", subscription=" + subscription +
                ", created_with_google=" + created_with_google +
                '}';
    }

    public Users() {
    }
}
