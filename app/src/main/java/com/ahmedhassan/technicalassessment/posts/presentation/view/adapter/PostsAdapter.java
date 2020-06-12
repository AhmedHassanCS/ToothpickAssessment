package com.ahmedhassan.technicalassessment.posts.presentation.view.adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedhassan.technicalassessment.R;
import com.ahmedhassan.technicalassessment.posts.domain.model.PostModel;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerSwipeAdapter<PostsAdapter.PostViewHolder> {

    private ArrayList<PostModel> postModels;
    private MutableLiveData<Pair<PostModel, Integer>> deletePostLiveData;
    private MutableLiveData<Pair<PostModel, Integer>> editPostLiveData;

    public PostsAdapter(ArrayList<PostModel> postModels){
        this.postModels = postModels;
        deletePostLiveData = new MutableLiveData<>();
        editPostLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(postModels.get(position), position);
    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public LiveData<Pair<PostModel, Integer>> getDeletePostLiveData() {
        return deletePostLiveData;
    }

    public LiveData<Pair<PostModel, Integer>> getEditPostLiveData() {
        return editPostLiveData;
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        private View view;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public void bind(PostModel postModel, int position){
            ((TextView) view.findViewById(R.id.tvPostTitle)).setText(postModel.getTitle());

            ((TextView) view.findViewById(R.id.tvPostContent)).setText(postModel.getBody());

            ((ImageView) view.findViewById(R.id.ivEditPost)).setOnClickListener(v -> {
                editPostLiveData.setValue(Pair.create(postModel, position));
            });

            ((ImageView) view.findViewById(R.id.ivDeletepost)).setOnClickListener(v -> {
                deletePostLiveData.setValue(Pair.create(postModel, position));
            });
        }
    }
}
