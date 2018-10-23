package com.nazar.kulyk_lab.adapters.holders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.adapters.holders.base.RecyclerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesItemViewHolder extends RecyclerViewHolder {
    @BindView(R.id.delete_fav_button)
    public Button button;

    public FavouritesItemViewHolder(@NonNull View item) {
        super(item);
        ButterKnife.bind(this.button);
    }
}
