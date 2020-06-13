package com.ahmedhassan.technicalassessment.postdetails.domain.repository;

import com.ahmedhassan.technicalassessment.postdetails.domain.model.PostDetailsModel;

import io.reactivex.Observable;

/**
 * Interface creation is to separate domain layer
 * Use cases interact with the interface without knowing about it's implementation
 * */
public interface PostDetailsRepository {
    Observable<PostDetailsModel> getPostDetails(int id);
}
