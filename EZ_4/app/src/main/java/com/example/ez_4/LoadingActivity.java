package com.example.ez_4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.ez_4.R;

public class LoadingActivity extends Activity {
    static final int CAMERA_PERMISSION_REQUEST = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        checkGrant();
    }

    private void checkGrant() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
            return;
        }

        startLoading();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case CAMERA_PERMISSION_REQUEST: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//
//                return;
//            }
//        }
        checkGrant();
    }

    private void startLoading(){   //// 로딩때
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { //딜레이 후 실행되는 내용
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class); //다음화면을 키는부분
                startActivity(intent);
                finish();//스플래시화면 종료
            }
        },1000); // 1초간 딜레이
    }

}
