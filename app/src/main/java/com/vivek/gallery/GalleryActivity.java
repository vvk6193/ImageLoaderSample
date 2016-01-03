package com.vivek.gallery;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.vivek.gallery.adapter.GalleryAdapter;
import com.vivek.gallery.interfaces.ClickListener;

import java.io.File;

public class GalleryActivity extends AppCompatActivity implements ClickListener {

    public static File[] imageFiles;
    RecyclerView recyclerView;
    GalleryAdapter adapter;
    boolean usePicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.gallery_recycler_view);
        int height = getResources().getDisplayMetrics().heightPixels;
        int width = getResources().getDisplayMetrics().widthPixels;
        final GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width/3,width/3);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager.setSpanCount(6);
            layoutParams.width = width/6;
            layoutParams.height = width/6;
        }
        recyclerView.setLayoutManager(layoutManager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        usePicasso = getIntent().getBooleanExtra("usePicasso",false);
        adapter = new GalleryAdapter(getApplicationContext(),imageFiles,this,usePicasso,layoutParams);
        recyclerView.setAdapter(adapter);

//        String CameraFolder="Camera";
//        File CameraDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString());
//        File[] files = CameraDirectory.listFiles();
//        for (File CurFile : files) {
//            if (CurFile.isDirectory()) {
//                Log.d("vvk","dir "+CurFile.getName() + " usePicasso " +usePicasso);
//                imageFiles = CurFile.listFiles();
//                if(imageFiles!=null && imageFiles.length > 20) {
//                    adapter = new GalleryAdapter(getApplicationContext(),imageFiles,this,usePicasso);
//                    recyclerView.setAdapter(adapter);
//                    break;
//                }
////                CameraDirectory=CurFile.getName();
//            } else {
//                Log.d("vvk","file "+CurFile.getName());
//            }
//        }

    }

    @Override
    public void itemClicked(int position, String type) {
        Intent i = new Intent(this,OpenImageActivity.class);
        i.putExtra("position",position);
        i.putExtra("usePicasso",usePicasso);
        startActivity(i);
    }
}
