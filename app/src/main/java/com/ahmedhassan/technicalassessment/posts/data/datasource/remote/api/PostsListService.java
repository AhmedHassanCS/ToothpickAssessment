package com.ahmedhassan.technicalassessment.posts.data.datasource.remote.api;

import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.PostEntity;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.params.CreatePostBody;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PostsListService {
    @GET("posts")
    Observable<Response<List<PostEntity>>> getPostsList(@Query("userId") int userId);

    @POST("posts")
    Observable<Response<PostEntity>> createPost(@Body CreatePostBody createPostBody);
}
