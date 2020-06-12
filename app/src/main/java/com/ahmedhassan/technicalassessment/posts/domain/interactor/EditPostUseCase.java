package com.ahmedhassan.technicalassessment.posts.domain.interactor;

import com.ahmedhassan.technicalassessment.core.domain.interactor.BaseUseCase;
import com.ahmedhassan.technicalassessment.posts.domain.model.PostModel;
import com.ahmedhassan.technicalassessment.posts.domain.repository.PostsListRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class EditPostUseCase extends BaseUseCase<EditPostUseCase.EditPostParams, PostModel> {

    private PostsListRepository postsListRepository;

    @Inject
    public EditPostUseCase(PostsListRepository postsListRepository){
        this.postsListRepository = postsListRepository;
    }

    @Override
    public Observable<PostModel> execute(EditPostParams editPostParams) {
        return postsListRepository.editPost(editPostParams.userId, editPostParams.id, editPostParams.title, editPostParams.body);
    }

    public static class EditPostParams{
        int id;
        int userId;
        String title;
        String body;

        public EditPostParams(int id, int userId, String title, String body) {
            this.id = id;
            this.userId = userId;
            this.title = title;
            this.body = body;
        }
    }
}
