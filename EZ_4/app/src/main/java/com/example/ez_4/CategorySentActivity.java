package com.example.ez_4;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CategorySentActivity extends AppCompatActivity {

    ImageView imgFruitResS, imgAnimalResS, imgVegetableResS,imgObjResS,imgAniResTwoS;
    TextView txtFruitS, txtAnimalS, txtVegetableS, txtObjectS, txtAniTwoS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_category_sent);

        imgFruitResS = findViewById(R.id.imgFruitResS);
        imgAnimalResS = findViewById(R.id.imgAnimalResS);
        imgVegetableResS = findViewById(R.id.imgVegetableResS);
        imgObjResS = findViewById(R.id.imgObjResS);
        imgAniResTwoS = findViewById(R.id.imgAniResTwoS);

        txtFruitS = findViewById(R.id.txtFruitS);
        txtAnimalS = findViewById(R.id.txtAnimalS);
        txtVegetableS = findViewById(R.id.txtVegetableS);
        txtObjectS = findViewById(R.id.txtObjectS);
        txtAniTwoS = findViewById(R.id.txtAniTwoS);

        txtFruitS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, SActivity.class);
                intent.putExtra("type", "과일");
                startActivity(intent);
            }
        });
        txtAnimalS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, SActivity.class);
                intent.putExtra("type", "동물");
                startActivity(intent);
            }
        });
        txtVegetableS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, SActivity.class);
                intent.putExtra("type", "채소");
                startActivity(intent);
            }
        });
        txtObjectS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, SActivity.class);
                intent.putExtra("type", "물건");
                startActivity(intent);
            }
        });
        txtAniTwoS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, SActivity.class);
                intent.putExtra("type", "동물2");
                startActivity(intent);
            }
        });




        imgFruitResS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, ResultActivity.class);
                intent.putExtra("type", "문장과일");
                startActivity(intent);
            }
        });
        imgAnimalResS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, ResultActivity.class);
                intent.putExtra("type", "문장동물");
                startActivity(intent);
            }
        });
        imgVegetableResS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, ResultActivity.class);
                intent.putExtra("type", "문장야채");
                startActivity(intent);
            }
        });
        imgObjResS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, ResultActivity.class);
                intent.putExtra("type", "문장사물");
                startActivity(intent);
            }
        });
        imgAniResTwoS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategorySentActivity.this, ResultActivity.class);
                intent.putExtra("type", "문장동물2");
                startActivity(intent);
            }
        });



    }
}