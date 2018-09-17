package com.knoxpo.khyati.easytowpractice.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.knoxpo.khyati.easytowpractice.R;
import com.knoxpo.khyati.easytowpractice.fragments.FoodFragment;
import com.knoxpo.khyati.easytowpractice.fragments.SideBarFragment;
import com.knoxpo.khyati.easytowpractice.fragments.SpaFragment;
import com.knoxpo.khyati.easytowpractice.models.SideItemLab;

public class MainActivity extends AppCompatActivity implements SideBarFragment.Callback {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        Fragment sideBar = getSupportFragmentManager().findFragmentById(R.id.sidebar_container);
        if(sideBar == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.sidebar_container, new SideBarFragment())
                    .commit();
        }

        Fragment mainFragment = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if(mainFragment == null){
            loadMainFragment(new FoodFragment());
        }
    }

    private void loadMainFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onMenuClicked(long menuId) {
        Fragment fragmentToSwitch = null;
        if(menuId == SideItemLab.SIDE_ITEM_FOOD_ID){
            fragmentToSwitch = new FoodFragment();
        }else if(menuId == SideItemLab.SIDE_ITEM_SPA_ID){
            fragmentToSwitch = new SpaFragment();
        }else{
            fragmentToSwitch = null;
        }

        loadMainFragment(fragmentToSwitch);

    }
}
