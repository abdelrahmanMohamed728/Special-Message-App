package com.example.specialmessage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
     ViewPagerAdapter viewPagerAdapter;
     ViewPager viewPager;
     TabLayout tab1;
     String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
         tab1 = findViewById(R.id.tabLayout);
         viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
         viewPager.setAdapter(viewPagerAdapter);
         tab1.setupWithViewPager(viewPager);
        id =getIntent().getStringExtra("id");
         tab1.getTabAt(0).setIcon(R.drawable.add);
         tab1.getTabAt(1).setIcon(R.drawable.edit);
    }
     String getId(){
        return id;
    }
}
