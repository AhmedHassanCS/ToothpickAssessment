package com.ahmedhassan.technicalassessment.postdetails.domain.repository;

import com.ahmedhassan.technicalassessment.postdetails.domain.model.PostDetailsModel;

import io.reactivex.Observable;

public interface PostDetailsRepository {
    Observable<PostDetailsModel> getPostDetails(int id);
}
