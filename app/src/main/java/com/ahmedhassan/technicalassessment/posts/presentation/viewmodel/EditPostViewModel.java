package com.ahmedhassan.technicalassessment.posts.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedhassan.technicalassessment.core.presentation.utils.ApplicationException;
import com.ahmedhassan.technicalassessment.posts.domain.interactor.EditPostUseCase;
import com.ahmedhassan.technicalassessment.core.domain.model.PostModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Shared ViewModel between EditPostFragment and PostsActivity
 * Fragment fires the fetching and the activity listens to it
 * */
public class EditPostViewModel extends ViewModel {

    private EditPostUseCase editPostUseCase;

    private MutableLiveData<PostModel> editedPostLiveData;
    private MutableLiveData<ApplicationException> editPostErrorLiveData;
    private MutableLiveData<Boolean> loadingLiveData;

    @Inject
    public EditPostViewModel(EditPostUseCase editPostUseCase){
        this.editPostUseCase = editPostUseCase;
        editedPostLiveData = new MutableLiveData<>();
        editPostErrorLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
    }

    public LiveData<PostModel> getEditedPostLiveData() {
        return editedPostLiveData;
    }

    public LiveData<ApplicationException> getEditPostErrorLiveData() {
        return editPostErrorLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public void editPost(int userId, int id, String title, String body){
        loadingLiveData.setValue(true);
        editPostUseCase.execute(new EditPostUseCase.EditPostParams(userId, id, title, body))
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
            editedPostLiveData.setValue(postModel);
        }

        @Override
        public void onError(Throwable e) {
            loadingLiveData.setValue(false);
            editPostErrorLiveData.setValue(ApplicationException.fromThrowable(e));
        }

        @Override
        public void onComplete() { }
    };
}
