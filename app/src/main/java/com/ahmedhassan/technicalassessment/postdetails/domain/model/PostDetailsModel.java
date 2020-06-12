package com.ahmedhassan.technicalassessment.postdetails.domain.model;

public class PostDetailsModel {
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostDetailsModel(int userId, int id, String title, String body) {
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
