package com.vivek.imageloadersample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.vivek.imageloadersample.adapter.GalleryPagerAdapter;


public class OpenImageActivity extends AppCompatActivity {

    ViewPager viewPager;
    boolean usePicasso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.gallery_view_pager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        usePicasso = getIntent().getBooleanExtra("usePicasso",false);
        int position = getIntent().getIntExtra("position", 0);
        Log.d("vvk","OpenImageActivity position is " + position + " usePicasso is " + usePicasso);
        GalleryPagerAdapter pagerAdapter = new GalleryPagerAdapter(getSupportFragmentManager(), getApplicationContext(), GalleryActivity.imageFiles,usePicasso);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
        viewPager.setOffscreenPageLimit(3);
//        viewPager.setPageMargin(200);
        viewPager.setPageMarginDrawable(R.drawable.ic_doctor_default_blue);
//        viewPager.setForeground(getResources().getDrawable(R.drawable.ic_doctor_default_blue,null));
//        viewPager.set
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("mytab","position is " + position + " positionoffset " + positionOffset + " offsetpixels " +positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("mytab","position is " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("mytab","state is " + state);
            }
        });

    }

}
