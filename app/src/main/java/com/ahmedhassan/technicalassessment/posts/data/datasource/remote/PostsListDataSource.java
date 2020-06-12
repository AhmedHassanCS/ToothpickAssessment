package com.ahmedhassan.technicalassessment.posts.data.datasource.remote;

import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.api.PostsListService;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.PostEntity;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.params.CreatePostBody;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

public class PostsListDataSource {

    private PostsListService postsListService;

    @Inject
    public PostsListDataSource(PostsListService postsListService){
        this.postsListService = postsListService;
    }

    public Observable<ArrayList<PostEntity>> getPostsList(int userId){
        return postsListService.getPostsList(userId).map(this::mapPosts);
    }

    public Observable<PostEntity> createPost(CreatePostBody createPostBody){
        return postsListService.createPost(createPostBody).map(this::mapCreatePost);
    }
    private ArrayList<PostEntity> mapPosts(Response<List<PostEntity>> response){
        if(response.body() != null)
            return new ArrayList<>(response.body());
        else return new ArrayList<>();
    }

    private PostEntity mapCreatePost(Response<PostEntity> response){
        return response.body();
    }

}
