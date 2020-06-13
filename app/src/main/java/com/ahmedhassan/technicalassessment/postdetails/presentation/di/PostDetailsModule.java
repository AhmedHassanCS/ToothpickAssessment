package com.ahmedhassan.technicalassessment.postdetails.presentation.di;

import com.ahmedhassan.technicalassessment.postdetails.data.datasource.remote.api.PostDetailsService;
import com.ahmedhassan.technicalassessment.postdetails.data.repository.PostDetailsRepositoryImpl;
import com.ahmedhassan.technicalassessment.postdetails.domain.repository.PostDetailsRepository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * PostDetailsModule is to provide service for the data-source
 * and repository implementation for the use-case
 * */
@Module
public class PostDetailsModule {

    @Provides
    public PostDetailsRepository providePostDetailsRepository(PostDetailsRepositoryImpl impl){
        return impl;
    }

    @Provides
    public PostDetailsService providePostDetailsService(Retrofit retrofit){
        return retrofit.create(PostDetailsService.class);
    }
}
