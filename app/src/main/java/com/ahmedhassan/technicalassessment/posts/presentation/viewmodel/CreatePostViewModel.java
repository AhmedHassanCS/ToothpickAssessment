package com.ahmedhassan.technicalassessment.posts.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedhassan.technicalassessment.core.presentation.utils.ApplicationException;
import com.ahmedhassan.technicalassessment.posts.domain.interactor.CreatePostUseCase;
import com.ahmedhassan.technicalassessment.core.domain.model.PostModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreatePostViewModel extends ViewModel {

    private MutableLiveData<PostModel> createdPostLiveData;
    private MutableLiveData<ApplicationException> createPostErrorLiveData;
    private MutableLiveData<Boolean> loadingLiveData;

    private CreatePostUseCase createPostUseCase;

    @Inject
    public CreatePostViewModel(CreatePostUseCase createPostUseCase){
        this.createPostUseCase = createPostUseCase;
        createdPostLiveData = new MutableLiveData<>();
        createPostErrorLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
    }

    public LiveData<ApplicationException> getCreatePostErrorLiveData() {
        return createPostErrorLiveData;
    }

    public LiveData<PostModel> getCreatedPostLiveData() {
        return createdPostLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public void createPost(int userId, String title, String body){
        loadingLiveData.setValue(true);
        createPostUseCase.execute(new CreatePostUseCase.CreatePostParams(userId, title, body))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(createPostObserver);
    }

    private Observer<PostModel> createPostObserver = new Observer<PostModel>(){

        @Override
        public void onSubscribe(Disposable d) { }

        @Override
        public void onNext(PostModel postModel) {
            loadingLiveData.setValue(false);
            createdPostLiveData.setValue(postModel);
        }

        @Override
        public void onError(Throwable e) {
            loadingLiveData.setValue(false);
            createPostErrorLiveData.setValue(ApplicationException.fromThrowable(e));
        }

        @Override
        public void onComplete() { }
    };
}
