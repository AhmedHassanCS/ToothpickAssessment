package com.ahmedhassan.technicalassessment.posts.domain.interactor;

import com.ahmedhassan.technicalassessment.core.domain.interactor.BaseUseCase;
import com.ahmedhassan.technicalassessment.posts.domain.model.PostModel;
import com.ahmedhassan.technicalassessment.posts.domain.repository.PostsListRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetPostsListUseCase extends BaseUseCase<Integer, ArrayList<PostModel>> {

    private PostsListRepository postsListRepository;

    @Inject
    public GetPostsListUseCase(PostsListRepository postsListRepository){
        this.postsListRepository = postsListRepository;
    }

    @Override
    public Observable<ArrayList<PostModel>> execute(Integer integer) {
        return postsListRepository.getPostsList(integer);
    }
}
