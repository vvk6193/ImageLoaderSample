package com.vivek.imageloadersample.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vivek.imageloadersample.fragment.ImageFragment;

import java.io.File;

/**
 * Created by vivek-pc on 12/15/2015.
 */
public class GalleryPagerAdapter extends FragmentPagerAdapter {


    File[] file;
    Context context;
    boolean usePicasso;
    public GalleryPagerAdapter(FragmentManager fm,Context context,File[] file,boolean usePicasso) {
        super(fm);
        this.file = file;
        this.context = context;
        this.usePicasso = usePicasso;
    }

    @Override
    public Fragment getItem(int position) {
        return new ImageFragment(file[position],context,usePicasso);
    }

    @Override
    public int getCount() {
        return file.length;
    }
}
