package com.ahmedhassan.technicalassessment.postdetails.presentation.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmedhassan.technicalassessment.R;

public class CreatePostFragment extends Fragment {


    public CreatePostFragment() {
        // Required empty public constructor
    }

    public static CreatePostFragment newInstance() {
        return new CreatePostFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false);
    }
}