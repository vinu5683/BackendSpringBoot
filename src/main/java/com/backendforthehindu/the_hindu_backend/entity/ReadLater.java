package com.backendforthehindu.the_hindu_backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "read_later")
public class ReadLater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "news_id")
    int news_id;
    @Column(name = "uid")
    int uid;

    @Override
    public String toString() {
        return "ReadLater{" +
                "id=" + id +
                ", news_id=" + news_id +
                ", uid=" + uid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public ReadLater() {
    }

    public ReadLater(int news_id, int uid) {
        this.news_id = news_id;
        this.uid = uid;
    }
}
