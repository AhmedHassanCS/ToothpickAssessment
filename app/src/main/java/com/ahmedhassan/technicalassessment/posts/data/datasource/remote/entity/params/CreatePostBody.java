package com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.params;

public class CreatePostBody {
    private int userId;
    private String title;
    private String body;

    public CreatePostBody(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
