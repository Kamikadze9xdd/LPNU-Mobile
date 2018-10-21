package com.nazar.kulyk_lab.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.fragment.ListItemsFragment;
import com.nazar.kulyk_lab.fragment.SectionsStatePagerAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListItemsFragment listItemsFragment = new ListItemsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, listItemsFragment);
        fragmentTransaction.commit();
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListItemsFragment(), "List Items");
        viewPager.setAdapter(adapter);
    }

}
