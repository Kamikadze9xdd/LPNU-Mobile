package com.nazar.kulyk_lab.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.models.artObjects.ArtObject;

public class DetailsItemFragment extends Fragment {
    private ArtObject artObject;

    public DetailsItemFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.item_details, container, false);

        return view;
    }
}
