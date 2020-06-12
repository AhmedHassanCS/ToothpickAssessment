package com.ahmedhassan.technicalassessment.posts.presentation.view.activity;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedhassan.technicalassessment.R;
import com.ahmedhassan.technicalassessment.core.presentation.viewmodel.ViewModelFactory;
import com.ahmedhassan.technicalassessment.posts.domain.model.PostModel;
import com.ahmedhassan.technicalassessment.posts.presentation.view.adapter.PostsAdapter;
import com.ahmedhassan.technicalassessment.posts.presentation.viewmodel.PostsViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class PostsActivity extends DaggerAppCompatActivity {

    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;

    private PostsAdapter adapter;

    private ArrayList<PostModel> postsList;

    @Inject
    ViewModelFactory<PostsViewModel> viewModelFactory;
    private PostsViewModel postsViewModel;

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

        observeData();
        observeError();
    }

    private void observeData(){
        postsViewModel.getPostsLiveData().observe(this, data ->{
            postsList.addAll(0, data);
            adapter.notifyItemRangeInserted(0, data.size());
        });
    }

    private void observeError(){
        postsViewModel.getPostsErrorLiveData().observe(this, e -> {
            showError(getString(e.getMessageResource()));
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

        });

        adapter.getEditPostLiveData().observe(this, pair ->{

        });
    }

    private void showError(String error){
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.clMainPostsLayout), error, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}