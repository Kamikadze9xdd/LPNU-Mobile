package com.nazar.kulyk_lab.adapters.holders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.adapters.holders.base.RecyclerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListItemViewHolder extends RecyclerViewHolder {
    @BindView(R.id.card_fav_button)
    public Button add_to_fav_button;
    @BindView(R.id.more_info_button)
    public Button more_info_button;
    public ListItemViewHolder(@NonNull View item) {
        super(item);
        ButterKnife.bind(this, item);
    }
}
