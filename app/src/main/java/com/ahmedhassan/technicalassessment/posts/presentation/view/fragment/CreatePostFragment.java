package com.ahmedhassan.technicalassessment.posts.presentation.view.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ahmedhassan.technicalassessment.R;
import com.ahmedhassan.technicalassessment.core.presentation.viewmodel.ViewModelFactory;
import com.ahmedhassan.technicalassessment.posts.presentation.viewmodel.CreatePostViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class CreatePostFragment extends Fragment {

    @BindView(R.id.etPostDesc)
    EditText etPostDesc;

    @BindView(R.id.etPostTitle)
    EditText etPostTitle;

    @Inject
    ViewModelFactory<CreatePostViewModel> viewModelFactory;
    private CreatePostViewModel createPostViewModel;

    public CreatePostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    public static CreatePostFragment newInstance() {
        return new CreatePostFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        createViewModel();
    }

    private void createViewModel(){
        if(getActivity() != null)
            createPostViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(CreatePostViewModel.class);
    }

    @OnClick(R.id.ivCloseBtn)
    void onCloseClick(View v){
        closeFragment();
    }

    private void closeFragment(){
        if(getActivity() != null)
            getActivity().onBackPressed();

        hideKeyboard();
    }

    private void hideKeyboard(){
        if(getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = getActivity().getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(getActivity());
            }
            if(imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.btnCreatePost)
    void onCreateBtnClick(){
        createPostViewModel.createPost(1, etPostTitle.getText().toString(), etPostDesc.getText().toString());
        closeFragment();
    }
}