package com.ahmedhassan.technicalassessment.posts.presentation.di;

import com.ahmedhassan.technicalassessment.posts.presentation.view.fragment.CreatePostFragment;
import com.ahmedhassan.technicalassessment.posts.presentation.view.fragment.EditPostFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PostsFragmentsBuilder {
    @ContributesAndroidInjector()
    abstract CreatePostFragment bindCreatePostFragment();

    @ContributesAndroidInjector()
    abstract EditPostFragment bindEditPostFragment();
}
