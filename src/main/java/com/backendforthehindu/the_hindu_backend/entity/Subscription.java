package com.backendforthehindu.the_hindu_backend.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "uid")
    int uid;
    @Column(name = "sub_date")
    Date sub_date;
    @Column(name = "exp_date")
    Date exp_date;

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", uid=" + uid +
                ", sub_date=" + sub_date +
                ", exp_date=" + exp_date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Date getSub_date() {
        return sub_date;
    }

    public void setSub_date(Date sub_date) {
        this.sub_date = sub_date;
    }

    public Date getExp_date() {
        return exp_date;
    }

    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }

    public Subscription() {
    }

    public Subscription(int uid, Date sub_date, Date exp_date) {
        this.uid = uid;
        this.sub_date = sub_date;
        this.exp_date = exp_date;
    }
}
