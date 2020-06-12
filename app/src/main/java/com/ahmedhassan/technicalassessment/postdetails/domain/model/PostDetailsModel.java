package com.ahmedhassan.technicalassessment.postdetails.domain.model;

public class PostDetailsModel {
    private int userId;
    private int it;
    private String title;
    private String body;

    public PostDetailsModel(int userId, int it, String title, String body) {
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
