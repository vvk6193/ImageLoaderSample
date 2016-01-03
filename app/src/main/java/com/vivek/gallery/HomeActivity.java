package com.vivek.gallery;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.vivek.gallery.adapter.FolderViewAdapter;
import com.vivek.gallery.interfaces.ClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ClickListener {

    List<File> fileList;
    RecyclerView recyclerView;
    FolderViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int height = getResources().getDisplayMetrics().heightPixels;
        int width = getResources().getDisplayMetrics().widthPixels;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fileList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.folder_recycler_view);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width/2,width/2);
        final GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager.setSpanCount(3);
            layoutParams.width = width/3;
            layoutParams.height = width/3;
        }
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FolderViewAdapter(getApplicationContext(),fileList,this,layoutParams);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(fileList.size() == 0) {
            String[] list;
            File CameraDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString());
            File[] files = CameraDirectory.listFiles();
            for (File CurFile : files) {
                if (CurFile.isDirectory()) {
                    list = CurFile.list();
                    if(list != null && list.length > 0) {
                        fileList.add(CurFile);
                        adapter.notifyItemInserted(fileList.size() - 1);
                    }
                } else {

                }
            }
        }
        mediaStore();
    }

    public void mediaStore() {
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN,
        };

        // content:// style URI for the "primary" external storage volume
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        // Make the query.
        Cursor cur = getContentResolver().query(images,
                projection, // Which columns to return
                null,       // Which rows to return (all rows)
                null,       // Selection arguments (none)
                null        // Ordering
        );

        Log.i("ListingImages"," query count="+cur.getCount());

        if (cur.moveToFirst()) {
            String bucket;
            String date;
            int bucketColumn = cur.getColumnIndex(
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

            int dateColumn = cur.getColumnIndex(
                    MediaStore.Images.Media.DATE_TAKEN);

            do {
                // Get the field values
                bucket = cur.getString(bucketColumn);
                date = cur.getString(dateColumn);

                // Do something with the values.
                Log.i("ListingImages", " bucket=" + bucket
                        + "  date_taken=" + date);
            } while (cur.moveToNext());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemClicked(int position, String type) {
        File file = fileList.get(position);
        GalleryActivity.imageFiles = file.listFiles();
        Intent i = new Intent(this,GalleryActivity.class);
        i.putExtra("position",position);
        i.putExtra("usePicasso",false);
        startActivity(i);
    }
}
