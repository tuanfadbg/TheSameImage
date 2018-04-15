package com.example.tuan.thesameimage;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuan.thesameimage.Adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class SelectImage extends AppCompatActivity {
    ArrayList<Integer> idImageList;
    ImageAdapter imageAdapter;
    GridView gridSelectImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_select_image);
        Intent intent = getIntent();
        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // display metric
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Window thisWindow = SelectImage.this.getWindow();
        thisWindow.setLayout(displayMetrics.widthPixels*8/10, displayMetrics.heightPixels*8/10);

        idImageList = intent.getIntegerArrayListExtra("idImageList");

        Collections.shuffle(idImageList);

        imageAdapter = new ImageAdapter(SelectImage.this, R.layout.item_grid_select_image, idImageList);
        gridSelectImage = findViewById(R.id.grid_select_image);
        gridSelectImage.setAdapter(imageAdapter);
        // select image
        gridSelectImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("idImageSelected", idImageList.get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}