package com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.api;

import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.entity.PostDetailsEntity;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Service interface is used to be inherited by retrofit auto generated classes
 * to execute the network request
 * */
public interface PostDetailsService {
    @GET("posts/{id}")
    Observable<Response<PostDetailsEntity>> getPostDetails(@Path("id") int id);
}
