package com.ahmedhassan.technicalassessment.core.di.component;

import com.ahmedhassan.technicalassessment.TechnicalAssessmentApplication;
import com.ahmedhassan.technicalassessment.core.di.module.ActivityBuilder;
import com.ahmedhassan.technicalassessment.core.di.module.AppModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class})

public interface AppComponent {
    void inject(TechnicalAssessmentApplication application);
}
