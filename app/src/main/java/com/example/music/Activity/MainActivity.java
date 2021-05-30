package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.music.Adapter.MainViewPagerAdapter;
import com.example.music.Fragment.Fragment_MoRong;
import com.example.music.Fragment.Fragment_Tim_Kiem;
import com.example.music.Fragment.Fragment_Trang_Chu;
import com.example.music.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        init();

    }

    private void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang Chu");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tim Kiem");
        mainViewPagerAdapter.addFragment(new Fragment_MoRong(),"Mo Rong");
        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
        tabLayout.getTabAt(2).setIcon(R.drawable.icontab);
    }

    private void AnhXa(){
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }
}