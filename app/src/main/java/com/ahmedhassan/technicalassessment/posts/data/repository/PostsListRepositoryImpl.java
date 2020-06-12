package com.ahmedhassan.technicalassessment.posts.data.repository;

import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.PostsListDataSource;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.entity.PostEntity;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.mapper.PostMapper;
import com.ahmedhassan.technicalassessment.posts.domain.model.PostModel;
import com.ahmedhassan.technicalassessment.posts.domain.repository.PostsListRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PostsListRepositoryImpl implements PostsListRepository {

    private PostsListDataSource postsListDataSource;

    @Inject
    public PostsListRepositoryImpl(PostsListDataSource postsListDataSource){
        this.postsListDataSource = postsListDataSource;
    }

    @Override
    public Observable<ArrayList<PostModel>> getPostsList(int userId) {
        return postsListDataSource.getPostsList(userId).map(this::map);
    }

    private ArrayList<PostModel> map(ArrayList<PostEntity> entities){
        ArrayList<PostModel> models = new ArrayList<PostModel>();
        for (PostEntity entity: entities) {
            models.add(PostMapper.mapPost(entity));
        }

        return models;
    }
}
