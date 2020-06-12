package com.ahmedhassan.technicalassessment.posts.data.datasource.remote;

import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.api.PostsListService;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.PostEntity;

import java.util.ArrayList;

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
        return postsListService.getPostsList(userId).map(this::map);
    }

    private ArrayList<PostEntity> map(Response<ArrayList<PostEntity>> response){
        return response.body();
    }
}
