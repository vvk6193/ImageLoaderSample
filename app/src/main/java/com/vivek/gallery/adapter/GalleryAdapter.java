package com.vivek.gallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.vivek.gallery.R;
import com.vivek.gallery.interfaces.ClickListener;
import com.vivek.imageloader.ImageLoader;

import java.io.File;

/**
 * Created by vivek-pc on 12/14/2015.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    File[] files;
    LayoutInflater inflater;
    Context context;
    ClickListener clicklistener;
    boolean usePicasso;
    LinearLayout.LayoutParams layoutParams;
    public GalleryAdapter(Context context, File[] files, ClickListener clickListener, boolean usePicasso, LinearLayout.LayoutParams layoutParams) {
        this.files = files;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.clicklistener = clickListener;
        this.usePicasso = usePicasso;
        this.layoutParams = layoutParams;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_gallery,null),clicklistener,layoutParams);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Long t1 = System.currentTimeMillis();
        if(usePicasso) {
            Picasso.with(context).load(files[position]).placeholder(R.drawable.ic_doctor_default_blue).resize(300,300).centerCrop().into(holder.image);
        } else {
            ImageLoader.with(context).load(files[position]).initial(R.drawable.ic_doctor_default_blue).into(holder.image);
        }
//        Long t2 = System.currentTimeMillis();
//        Log.d("marotime","position " + position + " and time " + (t2-t1));

    }

    @Override
    public int getItemCount() {
        return files.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        ClickListener listener;
        public ViewHolder(View itemView, ClickListener clicklistener, LinearLayout.LayoutParams layoutParams) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.gallery_recyclerview_Image);
            image.setLayoutParams(layoutParams);
            itemView.setOnClickListener(this);
            this.listener = clicklistener;

        }

        @Override
        public void onClick(View v) {
            listener.itemClicked(getAdapterPosition(),"");
        }
    }
}
