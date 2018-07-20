package com.eve.bookmarks.entitys;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "bookmark")
public class BookMarkMongo {
    @Id
    private String id;
    private String uid;
    private String value;
    private String version;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public BookMarkMongo() {
    }
    public BookMarkMongo(String value) {
        this.value = value;
    }
    public BookMarkMongo(String value,String uid,String version) {
        this.value = value;
        this.uid = uid;
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
