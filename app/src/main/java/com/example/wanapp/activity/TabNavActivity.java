package com.example.wanapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.wanapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TabNavActivity extends AppCompatActivity {

    private ViewPager2 viewtabnav;
    private TabLayout tabLayout;

    List<String> titles = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_nav);
        viewtabnav.findViewById(R.id.nav_pager2);
        tabLayout.findViewById(R.id.tab_nav);

        //获得数据
    }
}