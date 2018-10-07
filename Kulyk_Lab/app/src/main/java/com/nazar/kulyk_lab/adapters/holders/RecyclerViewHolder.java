package com.nazar.kulyk_lab.adapters.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nazar.kulyk_lab.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.short_title)
    public TextView short_title;
    @BindView(R.id.long_title)
    public TextView long_title;
    @BindView(R.id.web_image)
    public ImageView web_image;
    @BindView(R.id.author)
    public TextView author;

    public RecyclerViewHolder(@NonNull View item) {
        super(item);
        ButterKnife.bind(this, item);
    }
}
