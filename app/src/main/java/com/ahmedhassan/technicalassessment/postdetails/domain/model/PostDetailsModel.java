package com.ahmedhassan.technicalassessment.postdetails.domain.model;

import com.ahmedhassan.technicalassessment.core.domain.model.PostModel;

/**
 * This class is not useful in our case but created for demonstration
 * Needed if details has more data than PostModel
 * */
public class PostDetailsModel extends PostModel {

    public PostDetailsModel(int userId, int id, String title, String body) {
        super(userId, id, title, body);
    }

}
