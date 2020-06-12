package com.ahmedhassan.technicalassessment.posts.data.datasource.remote.api;

import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.PostEntity;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostsListService {
    @GET("posts")
    Observable<Response<ArrayList<PostEntity>>> getPostsList(@Query("userId") int userId);
}
