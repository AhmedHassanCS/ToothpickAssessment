package com.ahmedhassan.technicalassessment.posts.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedhassan.technicalassessment.core.presentation.utils.ApplicationException;
import com.ahmedhassan.technicalassessment.posts.domain.interactor.DeletePostUseCase;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ViewModel create for functionality separation and to be able to reuse it
 * For demonstrating the architecture
 * */
public class DeletePostViewModel extends ViewModel {

    private DeletePostUseCase deletePostUseCase;

    private MutableLiveData<Integer> postDeleteLiveData;
    private MutableLiveData<ApplicationException> postDeleteErrorLiveData;

    @Inject
    public DeletePostViewModel(DeletePostUseCase deletePostUseCase){
        this.deletePostUseCase = deletePostUseCase;
        postDeleteLiveData = new MutableLiveData<>();
        postDeleteErrorLiveData = new MutableLiveData<>();
    }

    public LiveData<Integer> getPostDeleteLiveData() {
        return postDeleteLiveData;
    }

    public LiveData<ApplicationException> getPostDeleteErrorLiveData() {
        return postDeleteErrorLiveData;
    }

    public void deletePost(int id){
        deletePostUseCase.execute(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(deletePostObserver);
    }

    private Observer<Integer> deletePostObserver = new Observer<Integer>(){

        @Override
        public void onSubscribe(Disposable d) { }

        @Override
        public void onNext(Integer id) {
            postDeleteLiveData.setValue(id);
        }

        @Override
        public void onError(Throwable e) {
            postDeleteErrorLiveData.setValue(ApplicationException.fromThrowable(e));
        }

        @Override
        public void onComplete() { }
    };
}
