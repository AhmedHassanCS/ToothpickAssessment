package com.ahmedhassan.technicalassessment.posts.data.datasource.remote;

import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.api.PostsListService;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.PostEntity;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.params.CreatePostBody;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.params.EditPostParams;

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
        return postsListService.createPost(createPostBody).map(this::mapPost);
    }

    public Observable<PostEntity> editPost(EditPostParams editPostParams){
        return postsListService.editPost(editPostParams, editPostParams.getId()).map(this::mapPost);
    }

    public Observable<Integer> deletePost(int id){
        return postsListService.deletePost(id).map((response) -> id);
    }

    private ArrayList<PostEntity> mapPosts(Response<List<PostEntity>> response){
        if(response.body() != null)
            return new ArrayList<>(response.body());
        else return new ArrayList<>();
    }

    private PostEntity mapPost(Response<PostEntity> response){
        return response.body();
    }

}
