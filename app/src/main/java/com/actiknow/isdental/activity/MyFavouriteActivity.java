package com.actiknow.isdental.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.actiknow.isdental.R;
import com.actiknow.isdental.fragment.MyFavouriteEventFragment;
import com.actiknow.isdental.fragment.MyFavouriteExhibitorFragment;
import com.actiknow.isdental.fragment.MyFavouriteSessionFragment;
import com.actiknow.isdental.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyFavouriteActivity extends AppCompatActivity {
    ImageView ivBack;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ivBack = (ImageView) findViewById (R.id.ivBack);

    }

    private void initData() {
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
        Utils.setTypefaceToAllViews (this, ivBack);
    }

    private void initListener() {
        ivBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyFavouriteExhibitorFragment (), "EXHIBITOR");
        adapter.addFragment (new MyFavouriteEventFragment (), "EVENTS");
        adapter.addFragment (new MyFavouriteSessionFragment (), "SESSIONS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}