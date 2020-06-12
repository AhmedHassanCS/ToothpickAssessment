package com.ahmedhassan.technicalassessment;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import com.ahmedhassan.technicalassessment.core.presentation.di.component.AppComponent;
import com.ahmedhassan.technicalassessment.core.presentation.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.plugins.RxJavaPlugins;

public class TechnicalAssessmentApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    public DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent =
                DaggerAppComponent.builder().build();
        appComponent.inject(this);
        RxJavaPlugins.setErrorHandler(Throwable::printStackTrace);
    }
}
