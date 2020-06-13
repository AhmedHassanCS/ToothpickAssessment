package com.ahmedhassan.technicalassessment.posts.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedhassan.technicalassessment.core.presentation.utils.ApplicationException;
import com.ahmedhassan.technicalassessment.posts.domain.interactor.GetPostsListUseCase;
import com.ahmedhassan.technicalassessment.core.domain.model.PostModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * The main ViewModel for posts screen to fetch posts
 * */
public class PostsViewModel extends ViewModel {

    private GetPostsListUseCase getPostsListUseCase;

    private MutableLiveData<ArrayList<PostModel>> postsLiveData;
    private MutableLiveData<ApplicationException> postsErrorLiveData;

    @Inject
    public PostsViewModel(GetPostsListUseCase getPostsListUseCase){
        this.getPostsListUseCase = getPostsListUseCase;
        postsLiveData = new MutableLiveData<>();
        postsErrorLiveData = new MutableLiveData<>();
    }

    public LiveData<ArrayList<PostModel>> getPostsLiveData() {
        return postsLiveData;
    }

    public LiveData<ApplicationException> getPostsErrorLiveData() {
        return postsErrorLiveData;
    }

    public void getPosts(int userId){
        getPostsListUseCase.execute(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(postsObserver);
    }

    private Observer<ArrayList<PostModel>> postsObserver = new Observer<ArrayList<PostModel>>(){

        @Override
        public void onSubscribe(Disposable d) { }

        @Override
        public void onNext(ArrayList<PostModel> posts) {
            postsLiveData.setValue(posts);
        }

        @Override
        public void onError(Throwable e) {
            postsErrorLiveData.setValue(ApplicationException.fromThrowable(e));
        }

        @Override
        public void onComplete() { }
    };
}
