package com.ahmedhassan.technicalassessment.posts.presentation.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ahmedhassan.technicalassessment.R;
import com.ahmedhassan.technicalassessment.core.presentation.viewmodel.ViewModelFactory;
import com.ahmedhassan.technicalassessment.posts.presentation.viewmodel.EditPostViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class EditPostFragment extends Fragment {

    private static final String ARG_ID = "id";
    private static final String ARG_Title = "title";
    private static final String ARG_DESC = "desc";

    private int id;
    private String title;
    private String desc;

    @BindView(R.id.etPostDesc)
    EditText etPostDesc;

    @BindView(R.id.etPostTitle)
    EditText etPostTitle;

    @Inject
    ViewModelFactory<EditPostViewModel> viewModelFactory;
    private EditPostViewModel editPostViewModel;

    public EditPostFragment() {
        // Required empty public constructor
    }

    public static EditPostFragment newInstance(int id, String title, String desc) {
        EditPostFragment fragment = new EditPostFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_Title, title);
        args.putString(ARG_DESC, desc);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_post, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        getData();
        etPostTitle.setText(title);
        etPostDesc.setText(desc);
        createViewModel();
    }

    private void getData(){
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
            title = getArguments().getString(ARG_Title);
            desc = getArguments().getString(ARG_DESC);
        }
    }
    private void createViewModel(){
        if(getActivity() != null)
            editPostViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(EditPostViewModel.class);
    }


    @OnClick(R.id.btnEditPost)
    void editPost(){
        editPostViewModel.editPost(1, id, etPostTitle.getText().toString(), etPostDesc.getText().toString());
        closeFragment();
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
}