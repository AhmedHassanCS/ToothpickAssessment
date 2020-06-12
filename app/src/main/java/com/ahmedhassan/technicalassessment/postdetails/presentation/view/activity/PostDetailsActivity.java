package com.ahmedhassan.technicalassessment.postdetails.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.ahmedhassan.technicalassessment.R;
import com.ahmedhassan.technicalassessment.core.presentation.viewmodel.ViewModelFactory;
import com.ahmedhassan.technicalassessment.postdetails.domain.model.PostDetailsModel;
import com.ahmedhassan.technicalassessment.postdetails.presentation.viewmodel.PostDetailsViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class PostDetailsActivity extends DaggerAppCompatActivity {

    @BindView(R.id.tvPostDetailsTitle)
    TextView tvPostDetailsTitle;

    @BindView(R.id.tbPostDetailsDesc)
    TextView tbPostDetailsDesc;

    @BindView(R.id.rlProgress)
    RelativeLayout rlProgress;

    @BindView(R.id.tvDetailsReadMore)
    TextView tvDetailsReadMore;

    @Inject
    ViewModelFactory<PostDetailsViewModel> viewModelFactory;
    private PostDetailsViewModel postDetailsViewModel;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        ButterKnife.bind(this);
        setTitle(R.string.post_details_titles);
        init();
    }

    private void init(){
        createViewModel();
        getArguments();
        postDetailsViewModel.getPostDetails(id);
        observeData();
        observeError();
        observeLoading();
    }

    private void getArguments(){
        id = getIntent().getIntExtra(ID_PARAM, 1);
    }

    private void createViewModel(){
        postDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostDetailsViewModel.class);
    }

    private void observeData(){
        postDetailsViewModel.getPostDetailsLiveData().observe(this, model ->{
            tvPostDetailsTitle.setText(model.getTitle());
            tbPostDetailsDesc.setText(model.getBody());
        });
    }

    private void observeError(){
        postDetailsViewModel.getPostDetailsErrorLiveData().observe(this, e ->{
            showError(getString(e.getMessageResource()));
            findViewById(R.id.clMainPostDetailsLayout).setVisibility(View.INVISIBLE);
        });
    }

    private void observeLoading(){
        postDetailsViewModel.getPostDetailsLoadingLiveData().observe(this, loading ->{
            if(loading)
                rlProgress.setVisibility(View.VISIBLE);
            else rlProgress.setVisibility(View.GONE);
        });
    }

    @OnClick(R.id.tvDetailsReadMore)
    void onReadMoreClick(View view){
        if(postDetailsViewModel.isReadMoreApplied){
            tbPostDetailsDesc.setMaxLines(2);
            tvDetailsReadMore.setText(R.string.details_read_more);
            postDetailsViewModel.isReadMoreApplied = false;
        } else {
            tbPostDetailsDesc.setMaxLines(Integer.MAX_VALUE);
            tvDetailsReadMore.setText(R.string.details_read_less);
            postDetailsViewModel.isReadMoreApplied = true;
        }
    }

    private void showError(String error){
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.clMainPostDetailsLayout), error, Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.colorRoseRed));
        snackbar.show();
    }

    public static String ID_PARAM = "id";
    public static Intent getCallingIntent(Context context, int id){
        Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra(ID_PARAM, id);
        return intent;
    }
}