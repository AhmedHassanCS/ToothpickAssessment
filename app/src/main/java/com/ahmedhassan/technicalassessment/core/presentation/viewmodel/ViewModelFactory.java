package com.ahmedhassan.technicalassessment.core.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class ViewModelFactory<T extends ViewModel> implements ViewModelProvider.Factory{

    T viewModel;

    @Inject
    public ViewModelFactory(T viewModel){
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModel;
    }
}
