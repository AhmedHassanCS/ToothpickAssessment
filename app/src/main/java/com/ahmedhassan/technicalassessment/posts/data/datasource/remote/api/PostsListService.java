package com.ahmedhassan.technicalassessment.posts.data.datasource.remote.api;

import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.PostEntity;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.params.CreatePostBody;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.params.EditPostParams;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostsListService {
    @GET("posts")
    Observable<Response<List<PostEntity>>> getPostsList(@Query("userId") int userId);

    @POST("posts")
    Observable<Response<PostEntity>> createPost(@Body CreatePostBody createPostBody);

    @PUT("posts/{id}")
    Observable<Response<PostEntity>> editPost(@Body EditPostParams editPostParams, @Path("id") int id);

    @DELETE("posts/{id}")
    Observable<Response<Void>> deletePost(@Path("id") int id);
}
