package com.ahmedhassan.technicalassessment.posts.presentation.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedhassan.technicalassessment.R;
import com.ahmedhassan.technicalassessment.core.presentation.viewmodel.ViewModelFactory;
import com.ahmedhassan.technicalassessment.posts.domain.model.PostModel;
import com.ahmedhassan.technicalassessment.posts.presentation.view.adapter.PostsAdapter;
import com.ahmedhassan.technicalassessment.posts.presentation.view.fragment.CreatePostFragment;
import com.ahmedhassan.technicalassessment.posts.presentation.viewmodel.CreatePostViewModel;
import com.ahmedhassan.technicalassessment.posts.presentation.viewmodel.PostsViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class PostsActivity extends DaggerAppCompatActivity {

    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;

    @BindView(R.id.viewShadow)
    View viewShadow;

    @BindView(R.id.rlProgress)
    RelativeLayout rlProgress;

    private PostsAdapter adapter;

    private ArrayList<PostModel> postsList;

    @Inject
    ViewModelFactory<PostsViewModel> viewModelFactory;
    private PostsViewModel postsViewModel;


    @Inject
    ViewModelFactory<CreatePostViewModel> createViewModelFactory;
    private CreatePostViewModel createPostViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Dagger dependency injection
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);


        //butter knife dependency injection
        ButterKnife.bind(this);

        init();
    }

    private void init(){
        setupRecyclerView();
        initViewModel();
        observePostActions();
        postsViewModel.getPosts(1);
    }
    private void initViewModel(){
        postsViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel.class);
        createPostViewModel = ViewModelProviders.of(this, createViewModelFactory).get(CreatePostViewModel.class);

        observeData();
        observeError();
        observeProgress();
    }

    private void observeData(){
        postsViewModel.getPostsLiveData().observe(this, data ->{
            postsList.addAll(0, data);
            adapter.notifyItemRangeInserted(0, data.size());
        });

        createPostViewModel.getCreatedPostLiveData().observe(this, postModel ->{
            postsList.add(0, postModel);
            adapter.notifyItemInserted(0);
            rvPosts.scrollToPosition(0);
        });
    }

    private void observeError(){
        postsViewModel.getPostsErrorLiveData().observe(this, e -> {
            showError(getString(e.getMessageResource()));
        });

        createPostViewModel.getCreatePostErrorLiveData().observe(this, t ->{
            showError(getString(t.getMessageResource()));
        });
    }

    private void observeProgress(){
        createPostViewModel.getLoadingLiveData().observe(this, loading -> {
            if(loading)
                rlProgress.setVisibility(View.VISIBLE);
            else rlProgress.setVisibility(View.GONE);
        });
    }
    private void setupRecyclerView(){
        rvPosts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        postsList = new ArrayList<>();
        adapter = new PostsAdapter(postsList);
        rvPosts.setAdapter(adapter);
    }

    private void observePostActions(){
        adapter.getDeletePostLiveData().observe(this, pair ->{
            //TODO delete post
        });

        adapter.getEditPostLiveData().observe(this, pair ->{
            //TODO edit post
        });
    }

    @OnClick(R.id.fabCreatePost)
    void onCreatePostClick(View view){
        viewShadow.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out)
                .replace(R.id.flCreateFragmentContainer, CreatePostFragment.newInstance())
                .addToBackStack("create")
                .commit();
    }

    @OnClick(R.id.viewShadow)
    void onShadowClicked(){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        viewShadow.setVisibility(View.GONE);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flCreateFragmentContainer);
        if(fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        else
            super.onBackPressed();
    }

    private void showError(String error){
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.clMainPostsLayout), error, Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.colorRoseRed));
        snackbar.show();
    }
}