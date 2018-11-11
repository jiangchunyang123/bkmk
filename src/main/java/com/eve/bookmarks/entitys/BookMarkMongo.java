package com.eve.bookmarks.entitys;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookmark")
public class BookMarkMongo {
    @Id
    private String id;
    private String userName;
    private String value;
    private Long version;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public BookMarkMongo() {
    }
    public BookMarkMongo(String value) {
        this.value = value;
    }
    public BookMarkMongo(String value,String userName,Long version) {
        this.value = value;
        this.userName = userName;
        this.version = version;
    }
    public BookMarkMongo(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
