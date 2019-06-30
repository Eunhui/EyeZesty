package com.example.ez_4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.ez_4.R;

public class TestRightActivity extends AppCompatActivity implements Runnable{

    ImageView topImage, bottomImage, leftImage, rightImage;
    int count = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_right);
        topImage=findViewById(R.id.topImage);
        bottomImage=findViewById(R.id.bottomImage);
        leftImage=findViewById(R.id.leftImage);
        rightImage=findViewById(R.id.rightImage);

        Thread th = new Thread(TestRightActivity.this);
        th.start();
    }

    @Override
    public void run() {
        int index=0;
        for (int i=count; i>0;  i--) {
            Message m = new Message();
            m.arg1 = index;
            pictureHandler.sendMessage(m);
            index++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent(TestRightActivity.this, MainActivity.class); //다음화면을 키는부분
        startActivity(intent);
        finish();


    }
    Handler pictureHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1 == 0){
                bottomImage.setVisibility(View.VISIBLE);
                topImage.setVisibility(View.INVISIBLE);
                leftImage.setVisibility(View.INVISIBLE);
                rightImage.setVisibility(View.INVISIBLE);
            }else if(msg.arg1 == 1){
                bottomImage.setVisibility(View.INVISIBLE);
                topImage.setVisibility(View.INVISIBLE);
                leftImage.setVisibility(View.VISIBLE);
                rightImage.setVisibility(View.INVISIBLE);
            }else if(msg.arg1 == 2){
                bottomImage.setVisibility(View.INVISIBLE);
                topImage.setVisibility(View.VISIBLE);
                leftImage.setVisibility(View.INVISIBLE);
                rightImage.setVisibility(View.INVISIBLE);
            }else{
                bottomImage.setVisibility(View.INVISIBLE);
                topImage.setVisibility(View.INVISIBLE);
                leftImage.setVisibility(View.INVISIBLE);
                rightImage.setVisibility(View.VISIBLE);
            }

        }
    };
}
