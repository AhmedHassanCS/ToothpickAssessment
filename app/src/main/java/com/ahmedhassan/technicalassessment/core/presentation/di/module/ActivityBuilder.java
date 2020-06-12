package com.ahmedhassan.technicalassessment.core.presentation.di.module;

import com.ahmedhassan.technicalassessment.postdetails.presentation.di.PostDetailsModule;
import com.ahmedhassan.technicalassessment.postdetails.presentation.view.activity.PostDetailsActivity;
import com.ahmedhassan.technicalassessment.posts.presentation.di.PostListModule;
import com.ahmedhassan.technicalassessment.posts.presentation.view.activity.PostsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {PostListModule.class})
    abstract PostsActivity bindPostsInjectorFactory();

    @ContributesAndroidInjector(modules = {PostDetailsModule.class})
    abstract PostDetailsActivity bindDetailsInjectorFactory();
}
