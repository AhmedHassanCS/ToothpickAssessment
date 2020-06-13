package com.ahmedhassan.technicalassessment.core.presentation.di.module;

import com.ahmedhassan.technicalassessment.postdetails.presentation.di.PostDetailsModule;
import com.ahmedhassan.technicalassessment.postdetails.presentation.view.activity.PostDetailsActivity;
import com.ahmedhassan.technicalassessment.posts.presentation.di.PostListModule;
import com.ahmedhassan.technicalassessment.posts.presentation.di.PostsFragmentsBuilder;
import com.ahmedhassan.technicalassessment.posts.presentation.view.activity.PostsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * ActivityBuilder is a dagger module to specify what modules will be needed for every activity
 * */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {PostListModule.class, PostsFragmentsBuilder.class})
    abstract PostsActivity bindPostsInjectorFactory();

    @ContributesAndroidInjector(modules = {PostDetailsModule.class})
    abstract PostDetailsActivity bindDetailsInjectorFactory();
}
