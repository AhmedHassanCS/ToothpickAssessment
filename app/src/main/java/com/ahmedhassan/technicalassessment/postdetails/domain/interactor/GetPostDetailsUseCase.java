package com.ahmedhassan.technicalassessment.postdetails.domain.interactor;

import com.ahmedhassan.technicalassessment.core.domain.interactor.BaseUseCase;
import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.entity.PostDetailsEntity;
import com.ahmedhassan.technicalassessment.postdetails.domain.model.PostDetailsModel;
import com.ahmedhassan.technicalassessment.postdetails.domain.repository.PostDetailsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetPostDetailsUseCase extends BaseUseCase<Integer, PostDetailsEntity> {

    private PostDetailsRepository postDetailsRepository;
    @Inject
    public GetPostDetailsUseCase(PostDetailsRepository postDetailsRepository){
        this.postDetailsRepository = postDetailsRepository;
    }

    @Override
    public Observable<PostDetailsEntity> execute(Integer param) {
        return postDetailsRepository.getPostDetails(param);
    }
}
