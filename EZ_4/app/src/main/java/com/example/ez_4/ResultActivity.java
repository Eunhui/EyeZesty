package com.example.ez_4;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ResultActivity extends AppCompatActivity {

    // 인스턴스 생성
    FirebaseStorage storage = FirebaseStorage.getInstance("gs://storage-b411f.appspot.com");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    StorageReference storageRef = storage.getReference();

    Context mContext;
    TextView txtNext;
    ImageView imgResult;
    int index = 0;
    StorageReference spaceRef = null;

    Drawable[] resultPic = new Drawable[5];
    Bitmap bm = null;
    BufferedInputStream bis = null;

    boolean sel = true;
    Bitmap img = null;
    String url = null;
    InputStream is = null;
    ByteArrayOutputStream baos = null;
    Object lock = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtNext = findViewById(R.id.txtNext);
        imgResult = findViewById(R.id.imgResult);
        Intent intent = getIntent();
        mContext = this;

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }

        final String type = intent.getExtras().getString("type");
        ImageView view;
        txtNext.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.CUPCAKE)
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public void onClick(View v) {
                if (index == 6) {
                    finish();
                }
                if (type.equals("단어동물")) {
                    ////// 배열에 결과 이미지 넣기
                    switch (index) {

                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimal0.png";
                                new httpTask_result().execute();
                            }
                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NO NULL**********");
                            }
                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimal0.png");

                            spaceRef = storageRef.child("heatmapimage/wanimal0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimal1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimal1.png");

                            spaceRef = storageRef.child("heatmapimage/wanimal2.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimal2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimal2.png");

                            spaceRef = storageRef.child("heatmapimage/wanimal2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimal3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }
                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimal3.png");

                            spaceRef = storageRef.child("heatmapimage/wanimal3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimal4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }
                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimal4.png");

                            spaceRef = storageRef.child("heatmapimage/wanimal4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }


                } else if (type.equals("단어과일")) {
                    switch (index) {
                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wfruit0.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }
                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wfruit0.png");

                            spaceRef = storageRef.child("heatmapimage/wfruit0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wfruit1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }
                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wfruit1.png");

                            spaceRef = storageRef.child("heatmapimage/wfruit1.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wfruit2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wfruit2.png");

                            spaceRef = storageRef.child("heatmapimage/wfruit2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wfruit3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wfruit3.png");

                            spaceRef = storageRef.child("heatmapimage/wfruit3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wfruit4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }
                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wfruit4.png");

                            spaceRef = storageRef.child("heatmapimage/wfruit4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }
                } else if (type.equals("단어야채")) {
                    switch (index) {
                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wvegetable0.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wvegetable0.png");

                            spaceRef = storageRef.child("heatmapimage/wvegetable0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wvegetable1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wvegetable1.png");

                            spaceRef = storageRef.child("heatmapimage/wvegetable1.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wvegetable2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wvegetable2.png");

                            spaceRef = storageRef.child("heatmapimage/wvegetable2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wvegetable3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wvegetable3.png");

                            spaceRef = storageRef.child("heatmapimage/wvegetable3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wvegetable4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wvegetable4.png");

                            spaceRef = storageRef.child("heatmapimage/wvegitable4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }
                } else if (type.equals("단어사물")) {
                    switch (index) {
                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wobject0.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wobject0.png");

                            spaceRef = storageRef.child("heatmapimage/wobject0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wobject1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wobject1.png");

                            spaceRef = storageRef.child("heatmapimage/wobject1.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wobject2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wobject2.png");

                            spaceRef = storageRef.child("heatmapimage/wobject2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wobject3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wobject3.png");

                            spaceRef = storageRef.child("heatmapimage/wobject3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wobject4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wobject4.png");

                            spaceRef = storageRef.child("heatmapimage/wobject4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }
                } else if (type.equals("단어동물2")) {
                    switch (index) {
                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimaltwo0.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimaltwo0.png");

                            spaceRef = storageRef.child("heatmapimage/wanimaltwo0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimaltwo1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimaltwo1.png");

                            spaceRef = storageRef.child("heatmapimage/wanimaltwo1.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimaltwo2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimaltwo2.png");

                            spaceRef = storageRef.child("heatmapimage/wanimaltwo2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimaltwo3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimaltwo3.png");

                            spaceRef = storageRef.child("heatmapimage/wanimaltwo3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/wanimaltwo4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/wanimaltwo4.png");

                            spaceRef = storageRef.child("heatmapimage/wanimaltwo4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }
                } else if (type.equals("문장동물")) {
                    switch (index) {
                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimal0.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimal0.png");

                            spaceRef = storageRef.child("heatmapimage/sanimal0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimal1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimal1.png");

                            spaceRef = storageRef.child("heatmapimage/sanimal1.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimal2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimal2.png");

                            spaceRef = storageRef.child("heatmapimage/sanimal2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimal3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimal3.png");

                            spaceRef = storageRef.child("heatmapimage/sanimal3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimal4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimal4.png");

                            spaceRef = storageRef.child("heatmapimage/sanimal4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }
                } else if (type.equals("문장과일")) {
                    switch (index) {
                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sfruit0.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sfruit0.png");

                            spaceRef = storageRef.child("heatmapimage/sfruit0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sfruit1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sfruit1.png");

                            spaceRef = storageRef.child("heatmapimage/sfruit1.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sfruit2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sfruit2.png");

                            spaceRef = storageRef.child("heatmapimage/sfruit2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sfruit3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sfruit3.png");

                            spaceRef = storageRef.child("heatmapimage/sfruit3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sfruit4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sfruit4.png");

                            spaceRef = storageRef.child("heatmapimage/sfruit4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }
                } else if (type.equals("문장야채")) {
                    switch (index) {
                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/svegetable0.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/svegetable0.png");

                            spaceRef = storageRef.child("heatmapimage/svegetable0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/svegetable1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/svegetable1.png");

                            spaceRef = storageRef.child("heatmapimage/svegetable1.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/svegetable2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/svegetable2.png");

                            spaceRef = storageRef.child("heatmapimage/svegetable2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/svegetable3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/svegetable3.png");

                            spaceRef = storageRef.child("heatmapimage/svegetable3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/svegetable4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }


                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/svegetable4.png");

                            spaceRef = storageRef.child("heatmapimage/svegitable4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }
                } else if (type.equals("문장사물")) {
                    switch (index) {
                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sobject0.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sobject0.png");

                            spaceRef = storageRef.child("heatmapimage/sobject0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sobject1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sobject1.png");

                            spaceRef = storageRef.child("heatmapimage/sobject1.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sobject2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sobject2.png");

                            spaceRef = storageRef.child("heatmapimage/sobject2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sobject3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sobject3.png");

                            spaceRef = storageRef.child("heatmapimage/sobject3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sobject4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sobject4.png");

                            spaceRef = storageRef.child("heatmapimage/sobject4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }
                } else if (type.equals("문장동물2")) {
                    switch (index) {
                        case 0:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimaltwo0.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimaltwo0.png");

                            spaceRef = storageRef.child("heatmapimage/sanimaltwo0.png");
                            break;
                        case 1:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimaltwo1.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimaltwo1.png");

                            spaceRef = storageRef.child("heatmapimage/sanimaltwo1.png");
                            break;
                        case 2:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimaltwo2.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimaltwo2.png");

                            spaceRef = storageRef.child("heatmapimage/sanimaltwo2.png");
                            break;
                        case 3:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimaltwo3.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimaltwo3.png");

                            spaceRef = storageRef.child("heatmapimage/sanimaltwo3.png");
                            break;
                        case 4:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                                url = "http://" + Utils.IP + "/sanimaltwo4.png";

                                new httpTask_result().execute();
                            }

                            try {
                                synchronized (lock) {
                                    lock.wait();
                                }
                                Log.e("WAIT", "wait");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //File file_path;
                            if (is == null) {
                                Log.e("NULL", "***********IMage ERROR************");
                            } else {
                                Log.e("NULL NOT", "***********NONONONON**********");
                            }

                            new SaveBitmapToFirebase().BitmapToFB("heatmapimage/sanimaltwo4.png");

                            spaceRef = storageRef.child("heatmapimage/sanimaltwo4.png");
                            break;
                        case 5:
                            txtNext.setText("돌아가기");
                            imgResult.setImageDrawable(getResources().getDrawable(R.drawable.graph));
                            break;
                    }
                }
                if (index < 5) {
                    downloadDirect(spaceRef, imgResult);
                }
                index++;
            }

        });
    }

    public void downloadDirect(StorageReference imageRef, ImageView imageView) {
        try {
            if (imageRef != null) {
                // Download directly from StorageReference using Glide
                // (See MyAppGlideModule for Loader registration)
                Glide.with(mContext)
                        .load(imageRef)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .signature(new ObjectKey(System.currentTimeMillis()))
                        .into(imageView);
            } else {
            }
        } catch (Exception e) {
            Log.e("GLIDE", "Glide error", e);
        }

    }

    class httpTask_result extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... param) {

            try {
                URL feedImage = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) feedImage.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();

                img = BitmapFactory.decodeStream(is);

                synchronized (lock) {
                    lock.notify();
                }
                Log.e("NOTIFICATION", "COMPLETE");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bm;
        }
    }

    class SaveBitmapToFirebase {
        public void BitmapToFB(String childUrl) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            try {
                imgResult.setImageBitmap(img);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //ImageView imageView=findViewById(R.id.imgFruitResS);
            if (img == null) {
                Log.e("IMAGE", "=============bitmap to fb null error==========");
            } else {
                Log.e("IMAGE NOT NULL", "=============bitmap to fb  ==========");
            }
            imgResult.setImageBitmap(img);

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://storage-b411f.appspot.com");

            StorageReference imagesRef = storageRef.child(childUrl);
            baos = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();
            final UploadTask uploadTask;

            uploadTask = imagesRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {

                @Override

                public void onFailure(@NonNull Exception exception) {

                    Log.v("알림", "사진 업로드 실패");

                    exception.printStackTrace();

                }

            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override

                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.v("알림", "사진 업로드 성공 ");

                }

            });

        }
    }

    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.e("Sign on success", "signInAnonymously:SUCCESS");
                // do your stuff
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e("SIGN in anonymously", "signInAnonymously:FAILURE", exception);
                    }
                });
    }

}