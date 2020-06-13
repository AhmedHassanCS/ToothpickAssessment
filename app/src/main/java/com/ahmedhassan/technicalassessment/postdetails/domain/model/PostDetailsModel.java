package com.ahmedhassan.technicalassessment.postdetails.domain.model;

import com.ahmedhassan.technicalassessment.core.domain.model.PostModel;

public class PostDetailsModel extends PostModel {

    public PostDetailsModel(int userId, int id, String title, String body) {
        super(userId, id, title, body);
    }

}
