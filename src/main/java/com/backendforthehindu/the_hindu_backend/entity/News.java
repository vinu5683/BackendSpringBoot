package com.backendforthehindu.the_hindu_backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "posted_at")
    String posted_at;

    @Column(name = "headline")
    String headline;

    @Column(name = "category_id")
    int category_id;

    @Column(name = "content")
    String content;

    @Column(name = "multi_media")
    String multi_media;

    @Column(name = "location")
    String location;

    @Column(name = "sub_heading")
    String sub_heading;

    public News(String posted_at, String headline, int category_id, String content, String multi_media, String location, String sub_heading, String author) {
        this.posted_at = posted_at;
        this.headline = headline;
        this.category_id = category_id;
        this.content = content;
        this.multi_media = multi_media;
        this.location = location;
        this.sub_heading = sub_heading;
        this.author = author;
    }

    @Column(name = "author")
    String author;

    public News() {
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", posted_at=" + posted_at +
                ", headline='" + headline + '\'' +
                ", category_id=" + category_id +
                ", content='" + content + '\'' +
                ", multi_media='" + multi_media + '\'' +
                ", location='" + location + '\'' +
                ", sub_heading='" + sub_heading + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosted_at() {
        return posted_at;
    }

    public void setPosted_at(String posted_at) {
        this.posted_at = posted_at;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMulti_media() {
        return multi_media;
    }

    public void setMulti_media(String multi_media) {
        this.multi_media = multi_media;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSub_heading() {
        return sub_heading;
    }

    public void setSub_heading(String sub_heading) {
        this.sub_heading = sub_heading;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
