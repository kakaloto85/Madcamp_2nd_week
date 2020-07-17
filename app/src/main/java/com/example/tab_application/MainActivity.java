package com.example.tab_application;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* basic operations for initializing activity itself */
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ViewPager vp = findViewById(R.id.viewpager);
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        // sync between tab and viewpager
        TabLayout tab = findViewById(R.id.tabs);
        tab.setupWithViewPager(vp);

        // add images on the tab
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.contact_default);
        images.add(R.drawable.gallery_default);
        images.add(R.drawable.calendar_default);
        for (int i = 0; i < 3; i++) {
            tab.getTabAt(i).setIcon(images.get(i));
        }
    }
}

