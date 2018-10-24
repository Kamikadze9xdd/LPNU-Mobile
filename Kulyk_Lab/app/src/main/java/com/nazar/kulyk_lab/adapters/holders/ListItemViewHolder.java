package com.nazar.kulyk_lab.adapters.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nazar.kulyk_lab.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.list_card)
    public CardView cardView;
    @BindView(R.id.short_title)
    public TextView short_title;
    @BindView(R.id.web_image)
    public ImageView web_image;
    @BindView(R.id.author)
    public TextView author;

    public ListItemViewHolder(@NonNull View item) {
        super(item);
        ButterKnife.bind(this, item);
    }
}
