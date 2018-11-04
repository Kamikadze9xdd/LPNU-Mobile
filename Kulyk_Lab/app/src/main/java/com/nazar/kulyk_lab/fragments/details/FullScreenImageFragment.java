package com.nazar.kulyk_lab.fragments.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nazar.kulyk_lab.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullScreenImageFragment extends Fragment {
    @BindView(R.id.full_screen_image)
    protected ImageView fullScreenImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.image, container, false);

        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();

        if(bundle != null){
            String link = (String) bundle.getSerializable("link");

            Picasso.get().load(link).into(fullScreenImage);
        }
        return view;
    }
}
