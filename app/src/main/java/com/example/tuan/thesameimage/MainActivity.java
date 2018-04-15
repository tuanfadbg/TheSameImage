 package com.example.tuan.thesameimage;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

 public class MainActivity extends AppCompatActivity {

     private static final int REQUEST_CODE = 11099;
     String[] nameImage;
     ArrayList<String> imageList;
     ArrayList<Integer> idImageList;
     ImageView imageFirst, imageSecond;
     int idImageFirst, idImageSecond;
     boolean isImageSecondClick = true;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        define();
        newGame();
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.new_game, menu);
         return super.onCreateOptionsMenu(menu);

     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         if (item.getItemId() == R.id.btn_reload && isImageSecondClick) {
             newGame();
         }
         return super.onOptionsItemSelected(item);
     }

     private void showPopupSelectImage() {
         Intent intent = new Intent(MainActivity.this, SelectImage.class);
         intent.putExtra("idImageList", idImageList);
         startActivityForResult(intent, REQUEST_CODE);
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int idImage = data.getIntExtra("idImageSelected", 0);
            imageSecond.setImageResource(idImage);
            int delayMillis  = 2000;
            isImageSecondClick = false;
            if (idImage == idImageFirst) {
                Toast.makeText(this, R.string.you_are_good, Toast.LENGTH_SHORT).show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isImageSecondClick = true;
                        newGame();
                    }
                }, delayMillis);
            }
            else {

                Toast.makeText(this, getString(R.string.you_chose_wrong), Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isImageSecondClick = true;
                            showPopupSelectImage();
                    }
                },delayMillis);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
     }

     private void newGame() {
         Random random = new Random();
         idImageFirst = idImageList.get(random.nextInt(idImageList.size()));
         idImageSecond = idImageList.get(random.nextInt(idImageList.size()));
         // imageSecond != imageFirst
         while (idImageSecond == idImageFirst) {
             idImageSecond = idImageList.get(random.nextInt(idImageList.size()));
         }

         imageFirst.setImageResource(idImageFirst);
         imageSecond.setImageResource(idImageSecond);
         imageSecond.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (isImageSecondClick) {
                     showPopupSelectImage();
                 }
             }
         });
     }

     private void define() {
        nameImage = getResources().getStringArray(R.array.list_image);
        // put array to array list
        imageList = new ArrayList<>(Arrays.asList(nameImage));
        idImageList = new ArrayList<>();

        // Shuffle image
        for (int i = 0; i < imageList.size(); i++) {
            idImageList.add(getResources().getIdentifier(imageList.get(i), "drawable", getPackageName()));
        }
        imageFirst = findViewById(R.id.imageFirst);
        imageSecond = findViewById(R.id.imageSecond);

    }
}