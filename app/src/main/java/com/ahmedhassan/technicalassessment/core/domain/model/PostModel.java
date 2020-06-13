package com.ahmedhassan.technicalassessment.core.domain.model;

import java.io.Serializable;

/**
 * PostModel contain the base minimum data of a post
 * It's shared between modules that's why it's in the core shared module
 * */
public class PostModel implements Serializable {
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostModel(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
