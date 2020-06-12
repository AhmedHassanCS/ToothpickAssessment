package com.ahmedhassan.technicalassessment.posts.presentation.di;

import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.api.PostDetailsService;
import com.ahmedhassan.technicalassessment.postdetails.data.repository.PostDetailsRepositoryImpl;
import com.ahmedhassan.technicalassessment.postdetails.domain.repository.PostDetailsRepository;
import com.ahmedhassan.technicalassessment.posts.data.datasource.remote.api.PostsListService;
import com.ahmedhassan.technicalassessment.posts.data.repository.PostsListRepositoryImpl;
import com.ahmedhassan.technicalassessment.posts.domain.repository.PostsListRepository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class PostListModule {
    @Provides
    public PostsListRepository providePostsListRepository(PostsListRepositoryImpl impl){
        return impl;
    }

    @Provides
    public PostsListService providePostsListService(Retrofit retrofit){
        return retrofit.create(PostsListService.class);
    }
}
