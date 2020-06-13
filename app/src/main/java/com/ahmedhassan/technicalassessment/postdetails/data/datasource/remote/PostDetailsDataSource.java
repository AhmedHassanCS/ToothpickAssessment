package com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote;

import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.api.PostDetailsService;
import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.entity.PostDetailsEntity;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;
/**
 * Remote data source to fetch data from server
 * */
public class PostDetailsDataSource {

    private PostDetailsService postDetailsService;
    @Inject
    public PostDetailsDataSource(PostDetailsService postDetailsService){
        this.postDetailsService = postDetailsService;
    }

    public Observable<PostDetailsEntity> getPostDetails(int id){
        return postDetailsService.getPostDetails(id).map(this::map);
    }

    private PostDetailsEntity map(Response<PostDetailsEntity> response){
        return response.body();
    }
}
