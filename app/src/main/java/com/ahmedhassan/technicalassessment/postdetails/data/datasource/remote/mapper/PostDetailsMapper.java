package com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.mapper;

import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.entity.PostDetailsEntity;
import com.ahmedhassan.technicalassessment.postdetails.domain.model.PostDetailsModel;

public class PostDetailsMapper {

    public static PostDetailsModel mapPostDetails(PostDetailsEntity entity){
        return new PostDetailsModel(entity.getUserId() ,entity.getId(), entity.getTitle(), entity.getBody());
    }
}
