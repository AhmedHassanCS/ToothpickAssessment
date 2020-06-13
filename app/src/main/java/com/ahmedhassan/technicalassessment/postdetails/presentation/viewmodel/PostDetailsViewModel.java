package com.ahmedhassan.technicalassessment.postdetails.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedhassan.technicalassessment.core.presentation.utils.ApplicationException;
import com.ahmedhassan.technicalassessment.postdetails.domain.interactor.GetPostDetailsUseCase;
import com.ahmedhassan.technicalassessment.postdetails.domain.model.PostDetailsModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/**
 * PostDetailsViewModel extends lifecycle ViewModel to be attached to the Activity and aware of its lifecycle
 * */
public class PostDetailsViewModel extends ViewModel {

    private GetPostDetailsUseCase getPostDetailsUseCase;

    private MutableLiveData<PostDetailsModel> postDetailsLiveData;
    private MutableLiveData<ApplicationException> postDetailsErrorLiveData;
    private MutableLiveData<Boolean> postDetailsLoadingLiveData;

    public boolean isReadMoreApplied = false;

    @Inject
    public PostDetailsViewModel(GetPostDetailsUseCase getPostDetailsUseCase){
        this.getPostDetailsUseCase = getPostDetailsUseCase;
        postDetailsLiveData = new MutableLiveData<>();
        postDetailsErrorLiveData = new MutableLiveData<>();
        postDetailsLoadingLiveData = new MutableLiveData<>();
    }

    public LiveData<PostDetailsModel> getPostDetailsLiveData() {
        return postDetailsLiveData;
    }

    public LiveData<ApplicationException> getPostDetailsErrorLiveData() {
        return postDetailsErrorLiveData;
    }

    public void getPostDetails(int id){
        postDetailsLoadingLiveData.setValue(true);
        getPostDetailsUseCase.execute(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(postDetailsObserver);
    }

    public MutableLiveData<Boolean> getPostDetailsLoadingLiveData() {
        return postDetailsLoadingLiveData;
    }

    private Observer<PostDetailsModel> postDetailsObserver = new Observer<PostDetailsModel>(){

        @Override
        public void onSubscribe(Disposable d) { }

        @Override
        public void onNext(PostDetailsModel postDetailsModel) {
            postDetailsLoadingLiveData.setValue(false);
            postDetailsLiveData.setValue(postDetailsModel);
        }

        @Override
        public void onError(Throwable e) {
            postDetailsLoadingLiveData.setValue(false);
            postDetailsErrorLiveData.setValue(ApplicationException.fromThrowable(e));
        }

        @Override
        public void onComplete() { }
    };
}
