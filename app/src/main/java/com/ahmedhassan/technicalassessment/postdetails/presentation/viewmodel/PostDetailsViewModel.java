package com.ahmedhassan.technicalassessment.postdetails.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedhassan.technicalassessment.core.presentation.utils.ApplicationException;
import com.ahmedhassan.technicalassessment.postdetails.domain.interactor.GetPostDetailsUseCase;
import com.ahmedhassan.technicalassessment.postdetails.domain.model.PostDetailsModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostDetailsViewModel extends ViewModel {

    private GetPostDetailsUseCase getPostDetailsUseCase;

    private MutableLiveData<PostDetailsModel> postDetailsLiveData;
    private MutableLiveData<ApplicationException> postDetailsErrorLiveData;

    public PostDetailsViewModel(GetPostDetailsUseCase getPostDetailsUseCase){
        this.getPostDetailsUseCase = getPostDetailsUseCase;
    }

    public void getPostDetails(int id){
        getPostDetailsUseCase.execute(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(postDetailsObserver);
    }

    private Observer<PostDetailsModel> postDetailsObserver = new Observer<PostDetailsModel>(){

        @Override
        public void onSubscribe(Disposable d) { }

        @Override
        public void onNext(PostDetailsModel postDetailsModel) {
            postDetailsLiveData.setValue(postDetailsModel);
        }

        @Override
        public void onError(Throwable e) {
            postDetailsErrorLiveData.setValue(ApplicationException.fromThrowable(e));
        }

        @Override
        public void onComplete() { }
    };
}
