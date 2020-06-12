package com.ahmedhassan.technicalassessment.posts.domain.repository;

import com.ahmedhassan.technicalassessment.posts.domain.model.PostModel;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface PostsListRepository {
    Observable<ArrayList<PostModel>> getPostsList(int userId);

    Observable<PostModel> createPost(int userId, String title, String body);
}
