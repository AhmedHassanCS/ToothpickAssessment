package com.ahmedhassan.technicalassessment.postdetails.data.repository;

import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.PostDetailsDataSource;
import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.entity.PostDetailsEntity;
import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.mapper.PostDetailsMapper;
import com.ahmedhassan.technicalassessment.postdetails.domain.model.PostDetailsModel;
import com.ahmedhassan.technicalassessment.postdetails.domain.repository.PostDetailsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PostDetailsRepositoryImpl implements PostDetailsRepository {

    private PostDetailsDataSource postDetailsDataSource;

    @Inject
    public PostDetailsRepositoryImpl(PostDetailsDataSource postDetailsDataSource){
        this.postDetailsDataSource = postDetailsDataSource;
    }

    @Override
    public Observable<PostDetailsModel> getPostDetails(int id) {
        return postDetailsDataSource.getPostDetails(id).map(this::map);
    }

    private PostDetailsModel map(PostDetailsEntity postDetailsEntity){
        return PostDetailsMapper.mapPostDetails(postDetailsEntity);
    }
}
