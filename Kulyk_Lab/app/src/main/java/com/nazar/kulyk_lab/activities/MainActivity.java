package com.nazar.kulyk_lab.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.fragments.favourities.FavouritesItemsFragment;
import com.nazar.kulyk_lab.fragments.list_all.ListItemsFragment;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ListItemsFragment listItemsFragment = new ListItemsFragment();
        setCurrentFragment(listItemsFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_button, menu);
        return true;
    }

    public void favoritesListDisplay(MenuItem item) {
        FavouritesItemsFragment favouritesItemsFragment = new FavouritesItemsFragment();
        setCurrentFragment(favouritesItemsFragment);
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
