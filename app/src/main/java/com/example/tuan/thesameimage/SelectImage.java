package com.example.tuan.thesameimage;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SelectImage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDialogSelectImage();


    }

    private void showDialogSelectImage() {
        Dialog selectBirdDialog = new Dialog(this);
        selectBirdDialog.setContentView(R.layout.grid_select_image);

    }
}
