package com.ahmedhassan.technicalassessment.posts.domain.interactor;

import com.ahmedhassan.technicalassessment.core.domain.interactor.BaseUseCase;
import com.ahmedhassan.technicalassessment.posts.domain.repository.PostsListRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DeletePostUseCase extends BaseUseCase<Integer, Integer> {

    private PostsListRepository postsListRepository;
    @Inject
    public DeletePostUseCase(PostsListRepository postsListRepository){
        this.postsListRepository = postsListRepository;
    }

    @Override
    public Observable<Integer> execute(Integer integer) {
        return postsListRepository.deletePost(integer);
    }
}
