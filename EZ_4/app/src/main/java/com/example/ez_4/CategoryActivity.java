package com.example.ez_4;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    ImageView imgFruitResult, imgAnimalResult, imgFoodResult,imgObjResOne, imgObjResTwo;
    TextView txtFruit, txtAnimal, txtFood, txtObjOne, txtAniTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        imgFruitResult = findViewById(R.id.imgFruitResult);
        imgAnimalResult = findViewById(R.id.imgAnimalResult);
        imgFoodResult = findViewById(R.id.imgFoodResult);
        imgObjResOne = findViewById(R.id.imgObjResOne);
        imgObjResTwo = findViewById(R.id.imgObjResTwo);

        txtFruit = findViewById(R.id.txtFruit);
        txtAnimal = findViewById(R.id.txtAnimal);
        txtFood = findViewById(R.id.txtFood);
        txtObjOne = findViewById(R.id.txtObjOne);
        txtAniTwo = findViewById(R.id.txtAniTwo);


        txtFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, WActivity.class);
                intent.putExtra("type", "과일");
                startActivity(intent);
            }
        });
        txtAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, WActivity.class);
                intent.putExtra("type", "동물");
                startActivity(intent);
            }
        });
        txtFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, WActivity.class);
                intent.putExtra("type", "채소");
                startActivity(intent);
            }
        });
        txtObjOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, WActivity.class);
                intent.putExtra("type", "물건");
                startActivity(intent);
            }
        });
        txtAniTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, WActivity.class);
                intent.putExtra("type", "동물2");
                startActivity(intent);
            }
        });




        imgFruitResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ResultActivity.class);
                intent.putExtra("type", "단어과일");
                startActivity(intent);
            }
        });
        imgAnimalResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ResultActivity.class);
                intent.putExtra("type", "단어동물");
                startActivity(intent);
            }
        });
        imgFoodResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ResultActivity.class);
                intent.putExtra("type", "단어야채");
                startActivity(intent);
            }
        });
        imgObjResOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ResultActivity.class);
                intent.putExtra("type", "단어사물");
                startActivity(intent);
            }
        });
        imgObjResTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ResultActivity.class);
                intent.putExtra("type", "단어동물2");
                startActivity(intent);
            }
        });



    }
}