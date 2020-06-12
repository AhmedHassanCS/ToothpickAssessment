package com.ahmedhassan.technicalassessment.postdetails.domain.repository;

import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.entity.PostDetailsEntity;

import io.reactivex.Observable;

public interface PostDetailsRepository {
    Observable<PostDetailsEntity> getPostDetails(int id);
}
