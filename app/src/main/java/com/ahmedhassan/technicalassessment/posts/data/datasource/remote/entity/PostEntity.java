package com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity;

/**
 * Ahmed Hassan
 * Creating an entity class to receive raw data before any manipulation  is better practice
 * In our case no manipulation will be made but created for layer separation and demonstration
 * */
public class PostEntity {
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostEntity(int userId, int id, String title, String body) {
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
}
