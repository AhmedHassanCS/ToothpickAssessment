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
import com.ahmedhassan.technicalassessment.posts.presentation.view.fragment.EditPostFragment;
import com.ahmedhassan.technicalassessment.posts.presentation.viewmodel.CreatePostViewModel;
import com.ahmedhassan.technicalassessment.posts.presentation.viewmodel.DeletePostViewModel;
import com.ahmedhassan.technicalassessment.posts.presentation.viewmodel.EditPostViewModel;
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

    @Inject
    ViewModelFactory<EditPostViewModel> editViewModelFactory;
    private EditPostViewModel editPostViewModel;

    @Inject
    ViewModelFactory<DeletePostViewModel> deleteViewModelFactory;
    private DeletePostViewModel deletePostViewModel;

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
        rlProgress.setVisibility(View.VISIBLE);
    }
    private void initViewModel(){
        postsViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel.class);
        createPostViewModel = ViewModelProviders.of(this, createViewModelFactory).get(CreatePostViewModel.class);
        editPostViewModel = ViewModelProviders.of(this, editViewModelFactory).get(EditPostViewModel.class);
        deletePostViewModel = ViewModelProviders.of(this, deleteViewModelFactory).get(DeletePostViewModel.class);

        observeData();
        observeError();
        observeProgress();
    }

    private void observeData(){
        postsViewModel.getPostsLiveData().observe(this, data ->{
            rlProgress.setVisibility(View.GONE);
            postsList.addAll(0, data);
            adapter.notifyItemRangeInserted(0, data.size());
        });

        createPostViewModel.getCreatedPostLiveData().observe(this, postModel ->{
            postsList.add(0, postModel);
            adapter.notifyItemInserted(0);
            rvPosts.scrollToPosition(0);
        });

        editPostViewModel.getEditedPostLiveData().observe(this, this::editPost);

        deletePostViewModel.getPostDeleteLiveData().observe(this, this::deletePost);
    }

    private void observeError(){
        postsViewModel.getPostsErrorLiveData().observe(this, e -> {
            showError(getString(e.getMessageResource()));
        });

        createPostViewModel.getCreatePostErrorLiveData().observe(this, t ->{
            showError(getString(t.getMessageResource()));
        });
    }

    private void editPost(PostModel postModel){
        int index = -1;
        for (int i = 0; i < postsList.size(); i++) {
            if (postsList.get(i).getId() == postModel.getId()) {
                postsList.get(i).setTitle(postModel.getTitle());
                postsList.get(i).setBody(postModel.getBody());
                index = i;
                break;
            }
        }
        if(index != -1){
            adapter.notifyItemChanged(index);
            rvPosts.scrollToPosition(index);
        }
    }

    private void deletePost(int id){
        rlProgress.setVisibility(View.GONE);

        int index = -1;
        for (int i = 0; i < postsList.size(); i++) {
            if (postsList.get(i).getId() == id) {
                postsList.remove(i);
                index = i;
                break;
            }
        }
        if(index != -1){
            adapter.notifyItemRemoved(index);
        }
    }
    private void observeProgress(){
        createPostViewModel.getLoadingLiveData().observe(this, loading -> {
            if(loading)
                rlProgress.setVisibility(View.VISIBLE);
            else rlProgress.setVisibility(View.GONE);
        });

        editPostViewModel.getLoadingLiveData().observe(this, loading -> {
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
            rlProgress.setVisibility(View.VISIBLE);
            deletePostViewModel.deletePost(pair.first.getId());
        });

        adapter.getEditPostLiveData().observe(this, pair ->{
            addEditFragment(pair.first);
        });
    }

    @OnClick(R.id.fabCreatePost)
    void onCreatePostClick(View view){
        viewShadow.setVisibility(View.VISIBLE);
        addCreateFragment();
    }

    private void addCreateFragment(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out)
                .replace(R.id.flCreateFragmentContainer, CreatePostFragment.newInstance())
                .addToBackStack("create")
                .commit();
    }

    private void addEditFragment(PostModel postModel){
        viewShadow.setVisibility(View.VISIBLE);
        Fragment fragment = EditPostFragment.newInstance(postModel.getId(), postModel.getTitle(), postModel.getBody());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out)
                .replace(R.id.flCreateFragmentContainer, fragment)
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