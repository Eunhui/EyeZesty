package com.example.ez_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ez_4.R;
import com.example.ez_4.CategoryActivity;
import com.example.ez_4.CategorySentActivity;


public class MainActivity extends AppCompatActivity {

    TextView txtWord, txtSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtWord = findViewById(R.id.txtWord);
        txtSentence = findViewById(R.id.txtSentence);

        txtWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////클릭시 이벤트
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        txtSentence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategorySentActivity.class);
                startActivity(intent);
            }
        });
    }
}
