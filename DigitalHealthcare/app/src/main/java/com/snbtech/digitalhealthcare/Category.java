package com.snbtech.digitalhealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Category extends AppCompatActivity {

    Button heart,brain,eye,hair,dentist,cancer,handicap,eair,bones,stomach;
    public static int listcnt=0;
    public static String lcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        heart=(Button)findViewById(R.id.civil1);
        brain=(Button)findViewById(R.id.electric);
        eye=(Button)findViewById(R.id.agri);
        hair=(Button)findViewById(R.id.furniture);
        dentist=(Button)findViewById(R.id.repairs);
        cancer=(Button)findViewById(R.id.home);
        handicap=(Button)findViewById(R.id.realestate);
        eair=(Button)findViewById(R.id.training);
        bones=(Button)findViewById(R.id.shopping);
        stomach=(Button)findViewById(R.id.cab);

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=1;
                lcategory="Heart";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });

        brain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=2;
                lcategory="Brain";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });

        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=3;
                lcategory="Eye";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });
        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=4;
                lcategory="Hair";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });

        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=5;
                lcategory="Pentist";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });

        cancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=6;
                lcategory="Cancer";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });

        handicap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=7;
                lcategory="Handicap";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });

        eair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=8;
                lcategory="Eair";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });


        bones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=9;
                lcategory="Shopping";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });

        stomach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listcnt=10;
                lcategory="Stomach";
                Intent i=new Intent(getApplicationContext(),SubCategoryList.class);
                startActivity(i);
            }
        });



    }
}



