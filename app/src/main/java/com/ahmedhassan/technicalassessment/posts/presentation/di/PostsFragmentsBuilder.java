package com.ahmedhassan.technicalassessment.posts.presentation.di;

import com.ahmedhassan.technicalassessment.posts.presentation.view.fragment.CreatePostFragment;
import com.ahmedhassan.technicalassessment.posts.presentation.view.fragment.EditPostFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
/**
 * PostsFragmentsBuilder define which module needed to create every fragment
 * It's required to provide this module to the activity that will contain those fragment
 * This is done by putting it the @ContributesAndroidInjector annotation of the activity binder in ActivityBuilder
 * If this module is not provided in ActivityBuilder dagger won't be able to inject fragments
 * */
@Module
public abstract class PostsFragmentsBuilder {
    @ContributesAndroidInjector()
    abstract CreatePostFragment bindCreatePostFragment();

    @ContributesAndroidInjector()
    abstract EditPostFragment bindEditPostFragment();
}
