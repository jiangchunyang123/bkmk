package com.eve.bookmarks.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 15,unique = true)
    private String uid;
    @Column(length = 10)
    private String uname;
    @Column(length = 40)
    private String password;
    @Column(length = 25)
    private String email;
    @Column(length = 20)
    private String phone;
    @Column(length = 1)
    private Integer state;
    @Column(length = 25)
    private String bookmarkMongoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBookmarkMongoId() {
        return bookmarkMongoId;
    }

    public void setBookmarkMongoId(String bookmarkMongoId) {
        this.bookmarkMongoId = bookmarkMongoId;
    }
}
