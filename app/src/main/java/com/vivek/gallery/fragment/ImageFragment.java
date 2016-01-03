package com.vivek.gallery.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vivek.gallery.R;
import com.vivek.imageloader.ImageLoader;

import java.io.File;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class ImageFragment extends BaseFragment {


    File file;
    Context context;
    boolean usePicasso;
    public ImageFragment(File file, Context context, boolean usePicasso) {
        this.file = file;
        this.context = context;
        this.usePicasso = usePicasso;
    }

    ImageView image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        image = (ImageView) v.findViewById(R.id.pager_fragment_image_view);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(usePicasso) {
            Picasso.with(context).load(file).placeholder(R.drawable.ic_doctor_default_blue).fit().into(image);
        } else {
            ImageLoader.with(context).load(file).initial(R.drawable.ic_doctor_default_blue).keepInCache(false).into(image);
        }
    }
}
