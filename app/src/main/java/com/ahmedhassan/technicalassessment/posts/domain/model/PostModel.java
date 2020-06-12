package com.ahmedhassan.technicalassessment.posts.domain.model;

public class PostModel {
    private int userId;
    private int it;
    private String title;
    private String body;

    public PostModel(int userId, int it, String title, String body) {
        this.userId = userId;
        this.it = it;
        this.title = title;
        this.body = body;
    }

    public int getIt() {
        return it;
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
