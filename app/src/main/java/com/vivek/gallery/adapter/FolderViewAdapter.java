package com.vivek.gallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vivek.gallery.R;
import com.vivek.gallery.interfaces.ClickListener;
import com.vivek.imageloader.ImageLoader;

import java.io.File;
import java.util.List;

/**
 * Created by vivek-pc on 12/19/2015.
 */
public class FolderViewAdapter extends RecyclerView.Adapter<FolderViewAdapter.FolderView> {

    private LayoutInflater inflater;
    private Context context;
    private List<File> files;
    ClickListener clickListener;
    LinearLayout.LayoutParams layoutParams;

    public FolderViewAdapter(Context context, List<File> files, ClickListener clickListener, LinearLayout.LayoutParams layoutParams) {
        this.context = context;
        this.files = files;
        this.layoutParams = layoutParams;
        this.clickListener = clickListener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public FolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_folder_view,parent,false);
        return new FolderView(v,clickListener,layoutParams);
    }

    @Override
    public void onBindViewHolder(FolderView holder, int position) {
        File file = files.get(position);
        File[] tmpfiles = file.listFiles();
        holder.name.setText(file.getName());
        holder.count.setText("" + tmpfiles.length);
        ImageLoader.with(context).load(tmpfiles[0]).initial(R.drawable.ic_doctor_default_blue).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class FolderView extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView name;
        TextView count;
        ClickListener clickListener;
        LinearLayout.LayoutParams layoutParams;
        public FolderView(View itemView,ClickListener clickListener,LinearLayout.LayoutParams layoutParams) {
            super(itemView);
            this.clickListener = clickListener;
            this.layoutParams = layoutParams;
            image = (ImageView) itemView.findViewById(R.id.folder_Image);
            image.setLayoutParams(layoutParams);
            name = (TextView) itemView.findViewById(R.id.folder_name);
            count = (TextView) itemView.findViewById(R.id.count);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.itemClicked(getAdapterPosition(),"");
        }
    }

}
