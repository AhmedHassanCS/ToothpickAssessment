package com.ahmedhassan.technicalassessment.core.presentation.di.component;

import com.ahmedhassan.technicalassessment.TechnicalAssessmentApplication;
import com.ahmedhassan.technicalassessment.core.presentation.di.module.ActivityBuilder;
import com.ahmedhassan.technicalassessment.core.presentation.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 *  Dagger AppComponent is to provide modules that will survive for the whole app session
 *
 * */
@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class})
public interface AppComponent {
    void inject(TechnicalAssessmentApplication application);
}
