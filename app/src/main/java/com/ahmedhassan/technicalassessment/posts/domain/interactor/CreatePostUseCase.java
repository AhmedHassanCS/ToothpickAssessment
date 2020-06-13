package com.ahmedhassan.technicalassessment.posts.domain.interactor;

import com.ahmedhassan.technicalassessment.core.domain.interactor.BaseUseCase;
import com.ahmedhassan.technicalassessment.core.domain.model.PostModel;
import com.ahmedhassan.technicalassessment.posts.domain.repository.PostsListRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CreatePostUseCase extends BaseUseCase<CreatePostUseCase.CreatePostParams, PostModel> {

    private PostsListRepository postsListRepository;

    @Inject
    public CreatePostUseCase(PostsListRepository postsListRepository){
        this.postsListRepository = postsListRepository;
    }

    @Override
    public Observable<PostModel> execute(CreatePostParams params) {
        return postsListRepository.createPost(params.userId, params.title, params.body);
    }

    public static class CreatePostParams{
        int userId;
        String title;
        String body;

        public CreatePostParams(int userId, String title, String body) {
            this.userId = userId;
            this.title = title;
            this.body = body;
        }
    }
}
