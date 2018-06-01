package com.eve.bookmarks.entitys;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "bookmark")
public class BookMarkMongo {
    @Id
    private String id;
    private String value;
    public BookMarkMongo() {
    }
    public BookMarkMongo(String value) {
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
