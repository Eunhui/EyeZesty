package com.example.ez_4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ez_4.R;

import java.io.IOException;
import java.util.ArrayList;


public class SActivity extends AppCompatActivity implements Runnable, SurfaceHolder.Callback  {
    Camera camera;

    ArrayList<MediaPlayer> mediaPlayerList = new ArrayList<>();
    ImageView imgLeft, imgRight;
    TextView txtWord;
    Context context;
    int count = 5; //문제 갯수
    Drawable[] leftPic = new Drawable[5]; //들어갈 사진 리스트(왼쪽)
    Drawable[] rightPic = new Drawable[5];
    String[] textList = new String[5]; //들어갈 단어

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_s);
        context= this;
        imgLeft=findViewById(R.id.imgLeft);
        imgRight=findViewById(R.id.imgRight);
        txtWord=findViewById(R.id.txtWord);
        Intent intent = getIntent();
        String type = intent.getExtras().getString("type");

        if(type.equals("동물")){
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.animal_10));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.animal_11));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.animal_12));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.animal_13));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.animal_14));


            leftPic[0]=getResources().getDrawable(R.drawable.chipmunk);
            leftPic[1]=getResources().getDrawable(R.drawable.cat);
            leftPic[2]=getResources().getDrawable(R.drawable.monkey);
            leftPic[3]=getResources().getDrawable(R.drawable.rabit);
            leftPic[4]=getResources().getDrawable(R.drawable.elephant);

            rightPic[0]=getResources().getDrawable(R.drawable.giraffe);
            rightPic[1]=getResources().getDrawable(R.drawable.dog);
            rightPic[2]=getResources().getDrawable(R.drawable.tiger);
            rightPic[3]=getResources().getDrawable(R.drawable.monkey);
            rightPic[4]=getResources().getDrawable(R.drawable.dinoo);

            textList[0]="목이 긴 동물은 누구인가요?";
            textList[1]="멍멍 짓는 동물은 누구인가요?";
            textList[2]="누구 울음 소리가 어흥 인가요?";
            textList[3]="거북이와 달리기 시합을 하는 동물은 누구인가요?";
            textList[4]="코로 밥을 먹는 나는 누구인가요?";

        }else if(type.equals("과일")){
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.fruit0));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.fruit1));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.fruit2));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.fruit4));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.fruit3));

            leftPic[0]=getResources().getDrawable(R.drawable.strawberry);
            leftPic[1]=getResources().getDrawable(R.drawable.pear);
            leftPic[2]=getResources().getDrawable(R.drawable.watermelon);
            leftPic[3]=getResources().getDrawable(R.drawable.lemon);
            leftPic[4]=getResources().getDrawable(R.drawable.banan);

            rightPic[0]=getResources().getDrawable(R.drawable.banan);
            rightPic[1]=getResources().getDrawable(R.drawable.orange);
            rightPic[2]=getResources().getDrawable(R.drawable.strawberry);
            rightPic[3]=getResources().getDrawable(R.drawable.pear);
            rightPic[4]=getResources().getDrawable(R.drawable.apple);

            textList[0]="원숭이가 좋아하는 과일은 무엇인가요?";
            textList[1]="사각사각한 과일은 무엇인가요?";
            textList[2]="겉과 속의 색이 다른 과일은 무엇인가요?";
            textList[3]="시큼새큼한 맛이 나는 과일은 무엇인가요?";
            textList[4]="백설공주가 먹은 과일은 무엇인가요?";



        }else if(type.equals("채소")){
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.veg0));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.veg1));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.veg2));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.veg3));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.veg4));

            leftPic[0]=getResources().getDrawable(R.drawable.pumkin);
            leftPic[1]=getResources().getDrawable(R.drawable.cucumber);
            leftPic[2]=getResources().getDrawable(R.drawable.sweetpotato);
            leftPic[3]=getResources().getDrawable(R.drawable.cabbage);
            leftPic[4]=getResources().getDrawable(R.drawable.carot);

            rightPic[0]=getResources().getDrawable(R.drawable.eggplant);
            rightPic[1]=getResources().getDrawable(R.drawable.chili);
            rightPic[2]=getResources().getDrawable(R.drawable.corn);
            rightPic[3]=getResources().getDrawable(R.drawable.tomato);
            rightPic[4]=getResources().getDrawable(R.drawable.chili);

            textList[0]="마녀의 집에 있는 나는 누구인가요?";
            textList[1]="누가 더 매울까요?";
            textList[2]="팝콘으로 변신하는 나는 누구인가요?";
            textList[3]="김치로 변신하는 나는 누구인가요?";
            textList[4]="토끼는 무엇을 더 좋아할까요?";

        }else if(type.equals("물건")){
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.object0));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.object1));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.object2));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.object3));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.object4));

            leftPic[0]=getResources().getDrawable(R.drawable.umbrella);
            leftPic[1]=getResources().getDrawable(R.drawable.car);
            leftPic[2]=getResources().getDrawable(R.drawable.glasses);
            leftPic[3]=getResources().getDrawable(R.drawable.cup);
            leftPic[4]=getResources().getDrawable(R.drawable.firecar);

            rightPic[0]=getResources().getDrawable(R.drawable.clock);
            rightPic[1]=getResources().getDrawable(R.drawable.ball);
            rightPic[2]=getResources().getDrawable(R.drawable.phone);
            rightPic[3]=getResources().getDrawable(R.drawable.desg);
            rightPic[4]=getResources().getDrawable(R.drawable.policecar);

            textList[0]="비가와요. 무엇이 필요할까요?";
            textList[1]="빵빵 나는 누구인가요?";
            textList[2]="눈이 안보여요. 무엇이 필요한가요?";
            textList[3]="물을 마시려면 무엇이 필요한가요?";
            textList[4]="불이났어요! 어디에 전화할까요?";
        }
        else if(type.equals("동물2")) {
            mediaPlayerList.add(MediaPlayer.create( SActivity.this,R.raw.animal_20));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.animal_21));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.animal_22));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.animal_23));
            mediaPlayerList.add(MediaPlayer.create(SActivity.this,R.raw.animal_24));

            leftPic[0]=getResources().getDrawable(R.drawable.dinoo);
            leftPic[1]=getResources().getDrawable(R.drawable.whale);
            leftPic[2]=getResources().getDrawable(R.drawable.chicken);
            leftPic[3]=getResources().getDrawable(R.drawable.fish);
            leftPic[4]=getResources().getDrawable(R.drawable.dino2);

            rightPic[0]=getResources().getDrawable(R.drawable.frog);
            rightPic[1]=getResources().getDrawable(R.drawable.duck);
            rightPic[2]=getResources().getDrawable(R.drawable.cow);
            rightPic[3]=getResources().getDrawable(R.drawable.chick);
            rightPic[4]=getResources().getDrawable(R.drawable.peng);

            textList[0]="티라노 사우르스의 가족은 누구인가요?";
            textList[1]="누가 바닷속에 살까요?";
            textList[2]="우유를 마시고싶어요. 누구한테 말할까요?";
            textList[3]="삐약삐약 나는 누구인가요?";
            textList[4]="남극에 사는 나는 누구일까요?";
        }

        Thread th = new Thread(SActivity.this);
        th.start();

    }

    @Override
    protected void onStart() {
        super.onStart();

        camera = null;

        int count = Camera.getNumberOfCameras();
        for (int id = 0; id < count; id++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(id, info);

            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    camera = Camera.open(id);
                }
                catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }

        if (camera != null) {
            SurfaceView surface = new SurfaceView(this);
            SurfaceHolder holder = surface.getHolder();
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            holder.addCallback(this);
        }
        else {
            Toast.makeText(this, "Cannot found front camera", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        camera.stopPreview();
        camera.release();
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
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //종료 후
        finish(); // 지금 화면 종료

    }
    @SuppressLint("HandlerLeak")
    Handler pictureHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            imgLeft.setImageDrawable(leftPic[msg.arg1]);
            imgRight.setImageDrawable(rightPic[msg.arg1]);
            txtWord.setText(textList[msg.arg1]);
            mediaPlayerList.get(msg.arg1).start();


        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}