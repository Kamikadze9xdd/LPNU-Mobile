package com.nazar.kulyk_lab.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.fragments.favourities.FavouritesItemsFragment;
import com.nazar.kulyk_lab.fragments.list_all.ListItemsFragment;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.tab_id)
//    TabLayout tabLayout;
//    @BindView(R.id.viewpager_id)
//    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ListItemsFragment listItemsFragment = new ListItemsFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, listItemsFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_button, menu);
        return true;
    }

    public void favoritesListDisplay(MenuItem item) {

        FavouritesItemsFragment favouritesItemsFragment = new FavouritesItemsFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, favouritesItemsFragment)
                .addToBackStack(null)
                .commit();
    }
}
