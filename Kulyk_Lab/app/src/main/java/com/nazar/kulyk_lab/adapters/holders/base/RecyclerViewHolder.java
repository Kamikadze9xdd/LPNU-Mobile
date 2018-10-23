package com.nazar.kulyk_lab.adapters.holders.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nazar.kulyk_lab.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.card)
    public CardView cardView;
    @BindView(R.id.short_title)
    public TextView short_title;
    @BindView(R.id.web_image)
    public ImageView web_image;
    @BindView(R.id.author)
    public TextView author;

    public RecyclerViewHolder(@NonNull View item) {
        super(item);
        ButterKnife.bind(this, item);
    }
}
