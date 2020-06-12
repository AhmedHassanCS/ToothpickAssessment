package com.ahmedhassan.technicalassessment.posts.data.datasource.remote.mapper;

import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.PostEntity;
import com.ahmedhassan.technicalassessment.posts.domain.model.PostModel;

public class PostMapper {

    public static PostModel mapPost(PostEntity entity){
        return new PostModel(entity.getUserId() ,entity.getId(), entity.getTitle(), entity.getBody());
    }
}
