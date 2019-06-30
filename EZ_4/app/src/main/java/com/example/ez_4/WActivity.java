package com.example.ez_4;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.nio.IntBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

import static android.graphics.Bitmap.createBitmap;
import static android.graphics.Bitmap.createScaledBitmap;

public class WActivity extends AppCompatActivity implements Runnable {
    ArrayList<MediaPlayer> mediaPlayerList = new ArrayList<>();
    ImageView imgLeft, imgRight;
    TextView txtWord;
    Context context;
    int count = 5; //문제 갯수
    Drawable[] leftPic = new Drawable[5]; //들어갈 사진 리스트(왼쪽)
    Drawable[] rightPic = new Drawable[5];
    String[] textList = new String[5]; //들어갈 단어
//    private final String urlPath = "https://172.20.10.4" + "/index.php";
    private final String urlPath = "http://" + Utils.IP + "/index.php";
    private final String TAG = "PHPTEST";
    private final String IMAGE_TAG = "IMAGETEST";
    Thread th;
    String type;
    protected boolean DBG = BuildConfig.DEBUG; // provide normal log output only in debug version

    protected FaceDetector faceDetector = null;
    protected GazeDetector gazeDetector = null;
    protected Bitmap mBitmap;
    protected Bitmap mEyeBitmap;
    protected int[] mDebugArray;
    protected byte[] mFrameArray;
    protected CameraSource mCameraSource = null;
    protected CameraSourcePreview mPreview;
    protected GraphicOverlay mGraphicOverlay;

    protected boolean DEBUG_MODE =
            true;
    //            false;
    //protected int eyeRegionWidth = 1920;
    //protected int eyeRegionHeight = 1200;
    protected int eyeRegionWidth = 80;
    protected int eyeRegionHeight = 60;
    protected int mDownSampleScale = 2;
    protected int mUpSampleScale = 4;
    protected int mDThresh = 10;
    protected double mGradThresh = 25.0;
    protected int iris_pixel = 0;
    protected int cx;
    protected int cy;
    protected int mUpThreshold = 8;
    protected int mDownThreshold = -4;
    protected int mLeftThreshold = 6;
    protected int mRightThreshold = -6;
    String name_server="WFRUIT";
    String ex_storage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
    String string_path = ex_storage + "/";


    /*private FileWriter fw = null;
    private BufferedWriter writer = null;
    private PrintWriter pwriter = null;*/

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_w);
        context = this;
        imgLeft = findViewById(R.id.imgLeft);
        imgRight = findViewById(R.id.imgRight);
        txtWord = findViewById(R.id.txtWord);
        Intent intent = getIntent();

        type = intent.getExtras().getString("type");
/*
        try {
            fw = new FileWriter(string_path + file_name);
            writer = new BufferedWriter(fw);
            pwriter = new PrintWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        if (type.equals("동물")) {
            name_server="wanimal";
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.chipmunk));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.cat));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.tiger));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.monkey));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.elephant));


            leftPic[0] = getResources().getDrawable(R.drawable.chipmunk);
            leftPic[1] = getResources().getDrawable(R.drawable.cat);
            leftPic[2] = getResources().getDrawable(R.drawable.monkey);
            leftPic[3] = getResources().getDrawable(R.drawable.rabit);
            leftPic[4] = getResources().getDrawable(R.drawable.giraffe);

            rightPic[0] = getResources().getDrawable(R.drawable.giraffe);
            rightPic[1] = getResources().getDrawable(R.drawable.dog);
            rightPic[2] = getResources().getDrawable(R.drawable.tiger);
            rightPic[3] = getResources().getDrawable(R.drawable.monkey);
            rightPic[4] = getResources().getDrawable(R.drawable.elephant);

            textList[0] = "다람쥐";
            textList[1] = "고양이";
            textList[2] = "호랑이";
            textList[3] = "원숭이";
            textList[4] = "코끼리";

        } else if (type.equals("과일")) {
            name_server="wfruit";
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.strawberry));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.pear));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.lemon));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.orange));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.apple));

            leftPic[0] = getResources().getDrawable(R.drawable.strawberry);
            leftPic[1] = getResources().getDrawable(R.drawable.pear);
            leftPic[2] = getResources().getDrawable(R.drawable.apple);
            leftPic[3] = getResources().getDrawable(R.drawable.orange);
            leftPic[4] = getResources().getDrawable(R.drawable.apple);

            rightPic[0] = getResources().getDrawable(R.drawable.apple);
            rightPic[1] = getResources().getDrawable(R.drawable.orange);
            rightPic[2] = getResources().getDrawable(R.drawable.lemon);
            rightPic[3] = getResources().getDrawable(R.drawable.pear);
            rightPic[4] = getResources().getDrawable(R.drawable.lemon);

            textList[0] = "딸기";
            textList[1] = "배";
            textList[2] = "레몬";
            textList[3] = "오렌지";
            textList[4] = "사과";


        } else if (type.equals("채소")) {
            name_server="wvegetable";
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.pumkin));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.cucumber));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.corn));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.cabage));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.tomato));

            leftPic[0] = getResources().getDrawable(R.drawable.pumkin);
            leftPic[1] = getResources().getDrawable(R.drawable.cucumber);
            leftPic[2] = getResources().getDrawable(R.drawable.sweetpotato);
            leftPic[3] = getResources().getDrawable(R.drawable.cabbage);
            leftPic[4] = getResources().getDrawable(R.drawable.tomato);

            rightPic[0] = getResources().getDrawable(R.drawable.eggplant);
            rightPic[1] = getResources().getDrawable(R.drawable.chili);
            rightPic[2] = getResources().getDrawable(R.drawable.corn);
            rightPic[3] = getResources().getDrawable(R.drawable.pumkin);
            rightPic[4] = getResources().getDrawable(R.drawable.carot);

            textList[0] = "호박";
            textList[1] = "오이";
            textList[2] = "옥수수";
            textList[3] = "배추";
            textList[4] = "토마토";

        } else if (type.equals("물건")) {
            name_server="wobject";
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.clock));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.car));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.phone));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.desk));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.bag));

            leftPic[0] = getResources().getDrawable(R.drawable.umbrella);
            leftPic[1] = getResources().getDrawable(R.drawable.car);
            leftPic[2] = getResources().getDrawable(R.drawable.umbrella);
            leftPic[3] = getResources().getDrawable(R.drawable.cup);
            leftPic[4] = getResources().getDrawable(R.drawable.meal);

            rightPic[0] = getResources().getDrawable(R.drawable.clock);
            rightPic[1] = getResources().getDrawable(R.drawable.ball);
            rightPic[2] = getResources().getDrawable(R.drawable.phone);
            rightPic[3] = getResources().getDrawable(R.drawable.desg);
            rightPic[4] = getResources().getDrawable(R.drawable.bag);

            textList[0] = "시계";
            textList[1] = "자동차";
            textList[2] = "전화기";
            textList[3] = "책상";
            textList[4] = "가방";
        } else if (type.equals("동물2")) {
            name_server="wanimaltwo";
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.dino));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.duck));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.chicken));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.fish));
            mediaPlayerList.add(MediaPlayer.create(WActivity.this, R.raw.pen));

            leftPic[0] = getResources().getDrawable(R.drawable.dinoo);
            leftPic[1] = getResources().getDrawable(R.drawable.whale);
            leftPic[2] = getResources().getDrawable(R.drawable.chicken);
            leftPic[3] = getResources().getDrawable(R.drawable.fish);
            leftPic[4] = getResources().getDrawable(R.drawable.peng);

            rightPic[0] = getResources().getDrawable(R.drawable.frog);
            rightPic[1] = getResources().getDrawable(R.drawable.duck);
            rightPic[2] = getResources().getDrawable(R.drawable.cow);
            rightPic[3] = getResources().getDrawable(R.drawable.chick);
            rightPic[4] = getResources().getDrawable(R.drawable.dino2);

            textList[0] = "공룡";
            textList[1] = "오리";
            textList[2] = "닭";
            textList[3] = "물고기";
            textList[4] = "펭귄";
        }

        th = new Thread(WActivity.this);
        th.start();

        getPermissions();

        mPreview = (CameraSourcePreview) findViewById(R.id.preview);
        mGraphicOverlay = (GraphicOverlay) findViewById(R.id.faceOverlay);
        faceDetector = new FaceDetector.Builder(getApplicationContext())
                .setTrackingEnabled(true)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .build();
        gazeDetector = new GazeDetector(faceDetector);
        gazeDetector.setProcessor(
                new MultiProcessor.Builder<>(new GraphicFaceTrackerFactory()).build());

        mCameraSource = new CameraSource.Builder(getApplicationContext(), gazeDetector)
                // .setRequestedPreviewSize(640, 480)
                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                .setRequestedFps(30.0f)
                .build();


    }


    private static final int REQ_PERMISSION_THISAPP = 0; // unique code for permissions request
    private boolean bUseCameraFlag = true;               // we want to use the camera
    private boolean bCameraPermissionGranted = false;   // have CAMERA permission

    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {            // need to ask at runtime as of Android 6.0
            String sPermissions[] = new String[2];    // space for possible permission strings
            int nPermissions = 0;    // count of permissions to be asked for
            if (bUseCameraFlag) {    // protection level: dangerous
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    bCameraPermissionGranted = true;
                else sPermissions[nPermissions++] = Manifest.permission.CAMERA;
            }
            if (nPermissions > 0) {
                if (nPermissions < sPermissions.length)
                    sPermissions = Arrays.copyOf(sPermissions, nPermissions);
                requestPermissions(sPermissions, REQ_PERMISSION_THISAPP);    // start the process
            }
        } else {    // in earlier API, permission is dealt with at install time, not run time
            if (bUseCameraFlag) bCameraPermissionGranted = true;
        }
    }

    //  Note: onRequestPermissionsResult happens *after* user has interacted with the permissions request
    //  So, annoyingly, have to now (re-)do things that didn't happen in onCreate() because permissions were not there yet.

    @Override
    // overrides method in android.app.Activity
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String TAG = "onRequestPermitResult";
        if (requestCode != REQ_PERMISSION_THISAPP) {    // check that this is a response to our request
            Log.e(TAG, "Unexpected requestCode " + requestCode);    // can this happen?
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        int n = grantResults.length;
        for (int i = 0; i < n; i++) {
            switch (permissions[i]) {
                case Manifest.permission.CAMERA:
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        bCameraPermissionGranted = true;
                        // redo the setup in onResume(...) ?
                    } else {
                        bUseCameraFlag = false;
                        String str = "You must grant CAMERA permission to use the camera!";
                        Log.e(TAG, str);
                    }
                    break;
            }
        }
    }

    private class GraphicFaceTrackerFactory implements MultiProcessor.Factory<Face> {
        @Override
        public Tracker<Face> create(Face face) {
            return new GraphicFaceTracker(mGraphicOverlay);
        }
    }

    /**
     * Face tracker for each detected individual. This maintains a face graphic within the app's
     * associated face overlay.
     */
    private class GraphicFaceTracker extends Tracker<Face> {
        private GraphicOverlay mOverlay;
        private FaceGraphic mFaceGraphic;

        GraphicFaceTracker(GraphicOverlay overlay) {
            mOverlay = overlay;
            mFaceGraphic = new FaceGraphic(overlay);
        }


        /**
         * Update the position/characteristics of the face within the overlay.
         */
        @Override
        public void onUpdate(FaceDetector.Detections<Face> detectionResults, Face face) {
            mOverlay.add(mFaceGraphic);
            mFaceGraphic.updateFace(face);
        }

        /**
         * Hide the graphic when the corresponding face was not detected.  This can happen for
         * intermediate frames temporarily (e.g., if the face was momentarily blocked from
         * view).
         */
        @Override
        public void onMissing(FaceDetector.Detections<Face> detectionResults) {
            mOverlay.remove(mFaceGraphic);
        }

        /**
         * Called when the face is assumed to be gone for good. Remove the graphic annotation from
         * the overlay.
         */
        @Override
        public void onDone() {
            mOverlay.remove(mFaceGraphic);
        }
    }

    /**
     * Graphic instance for rendering face position, orientation, and landmarks within an associated
     * graphic overlay view.
     */
    class FaceGraphic extends GraphicOverlay.Graphic {

        private static final float ID_TEXT_SIZE = 40.0f;
        private static final float BOX_STROKE_WIDTH = 5.0f;

        private final int COLOR_CHOICES[] = {
                Color.BLUE,
                Color.CYAN,
                Color.GREEN,
                Color.MAGENTA,
                Color.RED,
                Color.WHITE,
                Color.YELLOW
        };
        private int mCurrentColorIndex = 0;

        private Paint mFacePositionPaint;
        private Paint mIdPaint;
        private Paint mBoxPaint;

        private volatile Face mFace;

        FaceGraphic(GraphicOverlay overlay) {
            super(overlay);

            mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
            final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];

            mFacePositionPaint = new Paint();
            mFacePositionPaint.setColor(selectedColor);

            mIdPaint = new Paint();
            mIdPaint.setColor(selectedColor);
            mIdPaint.setTextSize(ID_TEXT_SIZE);

            mBoxPaint = new Paint();
            mBoxPaint.setColor(selectedColor);
            mBoxPaint.setStyle(Paint.Style.STROKE);
            mBoxPaint.setStrokeWidth(BOX_STROKE_WIDTH);
        }

        /**
         * Updates the face instance from the detection of the most recent frame.  Invalidates the
         * relevant portions of the overlay to trigger a redraw.
         */
        void updateFace(Face face) {
            mFace = face;
            postInvalidate();
        }

        /**
         * Draws the face annotations for position on the supplied canvas.
         */
        @Override
        public void draw(Canvas canvas) {
            int[] EyeArray = new int[2];
            Face face = mFace;
            if (face == null) {
                return;
            }
            // Draws a circle at the position of the detected face, with the face's track id below.
            float x = translateX(face.getPosition().x + face.getWidth() / 2);
            float y = translateY(face.getPosition().y + face.getHeight() / 2);

            // Draws a bounding box around the face.
            float xOffset = scaleX(face.getWidth() / 2.0f);
            float yOffset = scaleY(face.getHeight() / 2.0f);
            float left = x - xOffset;
            float top = y - yOffset;
            float right = x + xOffset;
            float bottom = y + yOffset;

            for (Landmark landmark : face.getLandmarks()) {
                int landmark_type = landmark.getType();

                if (landmark_type == Landmark.LEFT_EYE) {
                    cx = (int) translateX(landmark.getPosition().x);
                    cy = (int) translateY(landmark.getPosition().y);
                    Paint paint = new Paint();
                    paint.setColor(Color.GREEN);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(5);

                    // TODO(fyordan): These numbers are arbitray, probably should be proportional to face dimensions.

                    int testX = (int) landmark.getPosition().x;
                    System.out.println("TESTX : " + testX);
                    int textY = (int) landmark.getPosition().y;
                    System.out.println("TESTY : " + textY);
                    int eye_region_left = (int) landmark.getPosition().x - eyeRegionWidth / 2;
                    int eye_region_top = (int) landmark.getPosition().y - eyeRegionHeight / 2;

                    mEyeBitmap = toGrayscale(
                            createBitmap(mBitmap,
                                    eye_region_left,
                                    eye_region_top,
                                    eyeRegionWidth, eyeRegionHeight));

                    mEyeBitmap = createScaledBitmap(mEyeBitmap,
                            eyeRegionWidth / mDownSampleScale,
                            eyeRegionHeight / mDownSampleScale,
                            true);

                    iris_pixel = calculateEyeCenter(mEyeBitmap, mGradThresh, mDThresh);

                }
            }

            if (mEyeBitmap != null && DEBUG_MODE) {
                Bitmap debugBitmap = createBitmap(mEyeBitmap.getWidth() - 2, mEyeBitmap.getHeight() - 2, Bitmap.Config.ARGB_8888);//BitmapFactory.decodeByteArray(mDebugArray, 0, mDebugArray.length);
                debugBitmap.copyPixelsFromBuffer(IntBuffer.wrap(mDebugArray));
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(
//                        mEyeBitmap,
                        debugBitmap,
                        eyeRegionWidth * mUpSampleScale,
                        eyeRegionHeight * mUpSampleScale,
                        false);
                canvas.drawBitmap(resizedBitmap, 0, 0, mBoxPaint);
                int iris_x = iris_pixel % mEyeBitmap.getWidth() * mDownSampleScale * mUpSampleScale;
                int iris_y = iris_pixel / mEyeBitmap.getWidth() * mDownSampleScale * mUpSampleScale;
                canvas.drawCircle(iris_x, iris_y, mDownSampleScale * mUpSampleScale * mDThresh, mBoxPaint);
            }
            if (mEyeBitmap != null) {

                Paint paint = new Paint();
                paint.setColor(Color.GREEN);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(5);
                paint.setTextSize(60);
                int x_gaze = iris_pixel % mEyeBitmap.getWidth() - mEyeBitmap.getWidth() / 2;
                int y_gaze = mEyeBitmap.getHeight() / 2 - iris_pixel / mEyeBitmap.getWidth();

                y_gaze=y_gaze+400;
                if (x_gaze < mRightThreshold) {
                    canvas.drawText("x : " + x_gaze * 10 + " y : " + y_gaze * 10, 400, 200, paint);
                }
                if (x_gaze > mLeftThreshold) {
                    canvas.drawText("x : " + x_gaze * 10 + " y : " + y_gaze * 10, 400, 200, paint);
                }
                if (y_gaze > mUpThreshold) {
                    canvas.drawText("x : " + x_gaze * 10 + " y : " + y_gaze * 10, 400, 400, paint);
                }
                if (y_gaze < mDownThreshold) {
                    canvas.drawText("x : " + x_gaze * 10 + " y : " + y_gaze * 10, 400, 400, paint);
                }
                Log.e("EYE_DETECTED", x_gaze + "," + y_gaze);

                if(x_gaze < -5) {
                    x_gaze=x_gaze*(-1)+200;
                }
                else if(x_gaze>=-5){
                    x_gaze=x_gaze*(-50)+500;
                    if(x_gaze<0){
                        x_gaze*=(-1);
                    }
                }
                onEyeGazeDetect(x_gaze, y_gaze);

            }
        }


        protected int calculateEyeCenter(Bitmap eyeMap, double gradientThreshold, int d_thresh) {
            // TODO(fyordan): Shouldn't use mImageWidth and mImageHeight, but grayData dimensions.
            // Calculate gradients.
            // Ignore edges of image to not deal with boundaries.

            Log.e("CalculateEyeCenter", "Well it entered");
            int imageWidth = eyeMap.getWidth();
            int imageHeight = eyeMap.getHeight();
            int grayData[] = new int[imageWidth * imageHeight];
            double mags[] = new double[(imageWidth - 2) * (imageHeight - 2)];
            Log.e("CalculateEyeCenter", "Size is : " + imageWidth * imageHeight);
            eyeMap.getPixels(grayData, 0, imageWidth, 0, 0, imageWidth, imageHeight);
            double[][] gradients = new double[(imageWidth - 2) * (imageHeight - 2)][2];
            int k = 0;
            int magCount = 0;
            mDebugArray = new int[(imageWidth - 2) * (imageHeight - 2)];
            for (int j = 1; j < imageHeight - 1; j++) {
                for (int i = 1; i < imageWidth - 1; i++) {
                    int n = j * imageWidth + i;
                    gradients[k][0] = (grayData[n + 1] & 0xff) - (grayData[n] & 0xff);
                    gradients[k][1] = (grayData[n + imageWidth] & 0xff) - (grayData[n] & 0xff);
                    double mag = Math.sqrt(Math.pow(gradients[k][0], 2) + Math.pow(gradients[k][1], 2));
                    mags[k] = mag;
                    mDebugArray[k] = grayData[n];
                    if (mag > gradientThreshold) {
                        gradients[k][0] /= mag;
                        gradients[k][1] /= mag;
                        magCount++;
                        mDebugArray[k] = 0xffffffff;
                    } else {
                        gradients[k][0] = 0;
                        gradients[k][1] = 0;
                    }
                    k++;
                }
            }
            Log.e("CalculateEyeCenter", "imags above threshold: " + magCount);
            Log.e("CalculateEyeCenter", "Now we need to iterate through them all again");
            // For all potential centers
            int c_n = gradients.length / 2;
            double max_c = 0;
            for (int i = 1; i < imageWidth - 1; i++) {
                for (int j = 1; j < imageHeight - 1; j++) {
                    int n = j * imageWidth + i;
                    int k_left = Math.max(0, i - d_thresh - 1);
                    int k_right = Math.min(imageWidth - 2, i + d_thresh - 1);
                    int k_top = Math.max(0, j - d_thresh - 1);
                    int k_bottom = Math.min(imageHeight - 2, j + d_thresh - 1);
                    double sumC = 0;
                    for (int k_h = k_top; k_h < k_bottom; ++k_h) {
                        for (int k_w = k_left; k_w < k_right; ++k_w) {
                            k = k_w + k_h * (imageWidth - 2);
                            if ((gradients[k][0] == 0 && gradients[k][1] == 0)) continue;
                            double d_i = k_w - i;
                            double d_j = k_h - j;
                            if (Math.abs(d_i) > d_thresh || Math.abs(d_j) > d_thresh) continue;
                            double mag = Math.sqrt(Math.pow(d_i, 2) + Math.pow(d_j, 2));
                            if (mag > d_thresh) continue;
                            mag = mag == 0 ? 1 : mag;
                            d_i /= mag;
                            d_j /= mag;
                            sumC += Math.pow(d_i * gradients[k][0] + d_j * gradients[k][1], 2);
                        }
                    }
                    // TODO(fyordan): w_c should be the value in a gaussian filtered graydata
                    sumC /= (grayData[n] & 0xff);
                    if (sumC > max_c) {
                        c_n = n;
                        max_c = sumC;
                    }
                }
            }
            return c_n;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPreview.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bCameraPermissionGranted && (mCameraSource != null) && (mPreview != null)) {
            try {
                mPreview.start(mCameraSource, mGraphicOverlay);
            } catch (IOException e) {
                // WHO CARES
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraSource.release();
    }

    protected Bitmap toGrayscale(Bitmap bmp) {
        Bitmap grayscale = createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(grayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmp, 0, 0, paint);
        return grayscale;
    }

    class GazeDetector extends Detector<Face> {
        private Detector<Face> mDelegate;

        GazeDetector(Detector<Face> delegate) {
            mDelegate = delegate;
        }

        public SparseArray<Face> detect(Frame frame) {
            int w = frame.getMetadata().getWidth();
            int h = frame.getMetadata().getHeight();
            YuvImage yuvimage = new YuvImage(frame.getGrayscaleImageData().array(), ImageFormat.NV21, w, h, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            yuvimage.compressToJpeg(
                    new Rect(0, 0, w, h), 100, baos); // Where 100 is the quality of the generated jpeg
            mFrameArray = baos.toByteArray();
            mBitmap = BitmapFactory.decodeByteArray(mFrameArray, 0, mFrameArray.length);
            return mDelegate.detect(frame);
        }

        public boolean isOperational() {
            return mDelegate.isOperational();
        }

        public boolean setFocus(int id) {
            return mDelegate.setFocus(id);
        }
    }

    class httpThread implements Runnable {
        @Override
        public void run() {
            for (int j = 0; j < 5; j++) {
                Log.e(TAG, "TRY UPLOADING");
                FileInputStream inputStream = null;
                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;

                try {
                    Log.e(TAG, "TRY UPLOADING++" + j);
                    URL obj = new URL(urlPath + "?file=" +  name_server+ j + ".csv");
                    File file = new File("/sdcard/"+name_server + j + ".csv");
                    inputStream = new FileInputStream(file);
                    conn = (HttpURLConnection) obj.openConnection();
                    conn.setReadTimeout(1000);
                    conn.setConnectTimeout(1000);
                    conn.setRequestProperty("Connection", "close");
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty( "charset", "utf-8");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches( false );
                    conn.setDefaultUseCaches(false);
                    conn.connect();

//                    conn.setRequestProperty("Connection", "Keep-Alive");
//                    conn.setRequestProperty("ENCTYPE",
//                            "multipart/form-data");
//                    conn.setRequestProperty("Content-Type",
//                            "multipart/form-data;boundary=" + boundary);
//                    conn.setRequestProperty("file", name_server + j + ".csv");
//                    conn.setDoInput(true);
//                    conn.setDoOutput(true);
//                    conn.setUseCaches(false);

                    String urlParameters  = "data=";
                    byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
//                    long   postDataLength = postData.length;
//                    Log.e(TAG, "POST LENGTH: " + Long.toString(postDataLength + file.length()));
//                    conn.setRequestProperty( "Content-Length", Long.toString(postDataLength + file.length()));

                    dos = new DataOutputStream(conn.getOutputStream());
                    dos.write(postData);

                    // create a buffer of maximum size
                    bytesAvailable = inputStream.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];
                    // read file and write it into form...
                    bytesRead = inputStream.read(buffer, 0, bufferSize);

                    Log.e(TAG, "READ++");
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bytesRead);
                        bytesAvailable = inputStream.available();
                        bufferSize = Math
                                .min(bytesAvailable, maxBufferSize);
                        bytesRead = inputStream.read(buffer, 0,
                                bufferSize);
                    }
                    Log.e(TAG, "READ--");

                    // send multipart form eyeGazeData necesssary after file
                    // eyeGazeData...
                    // dos.writeBytes(lineEnd);

                    if (dos != null) {
                        dos.flush();
                        dos.close();
                        dos = null;
                    }
                    if (inputStream != null) {
                        inputStream.close();
                        inputStream = null;
                    }

//                    conn.disconnect();
                    // Responses from the server (code and message)
                    int serverResponseCode = conn.getResponseCode();

                    if (serverResponseCode == 200) {
                        Log.e(TAG, "START IMAGE EXPORT");
                    }
                    //System.out.println("END");
                    Log.e(TAG, "TRY UPLOADING--" + j);
                } catch (Exception e) {
                    Log.e(TAG, "Exception", e);
//                    j--;
                } finally {
                    try {
                        if (dos != null) {
                            dos.flush();
                            dos.close();
                            dos = null;
                        }
                        if (inputStream != null) {
                            inputStream.close();
                            inputStream = null;
                        }
                        if (conn != null) {
                            conn.disconnect();
                            conn = null;
                        }
                    } catch (Exception e) {
                        Log.w(TAG, "Warning during cleanup", e);
                        // Do nothing
                    }
                }

            }
        }
    }

    class GazeData {
        private int xGaze;
        private int yGaze;

        GazeData(int x_gaze, int y_gaze) {
            this.xGaze = x_gaze;
            this.yGaze = y_gaze;
        }

        public int getXGaze() {
            return xGaze;
        }

        public int getYGaze() {
            return yGaze;
        }

        public void setXGaze(int xGaze) {
            this.xGaze = xGaze;
        }

        public void setYGaze(int yGaze) {
            this.yGaze = yGaze;
        }
    }

    ArrayList eyeGazeData[] = new ArrayList[count];
    {
        for (int i = 0; i < count; i++) {
            eyeGazeData[i] = new ArrayList<GazeData>();
        }
    }
    private void clearEyeGazeData() {
        for (int i = 0; i < count; i++) {
            eyeGazeData[i] = new ArrayList<GazeData>();
        }
    }

    private int current_index = 0;

    public void onEyeGazeDetect(int x_gaze, int y_gaze) {
        if (current_index < 0) return;
        if (current_index >= count) return;

        synchronized (eyeGazeData[current_index]) {
            eyeGazeData[current_index].add(new GazeData(x_gaze, y_gaze));
        }
    }

    @Override
    public void run() {
        clearEyeGazeData();

        int index = 0;
        for (int i = count; i > 0; i--) {
            Message m = new Message();
            m.arg1 = index;
            pictureHandler.sendMessage(m);
            index++;

            try {
                Log.d("sleep", index + "");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
/*
        for (int i = 0; i < 5; i++) {
            Log.e("Gaze Data", i + "번째 단어");

            for (Object data : eyeGazeData[i]) {
                GazeData gazeData = (GazeData) data;

                Log.e("Gaze Data", gazeData.getXGaze() + ", " + gazeData.getYGaze());
            }
        }*/

        for (int i = 0; i < 5; i++) {
            try {
                File myfile = new File("/sdcard/"+name_server + i + ".csv");
                if (myfile.exists())
                    myfile.delete();
                myfile.createNewFile();
                FileOutputStream fOut = new FileOutputStream(myfile, true);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                for (Object data : eyeGazeData[i]) {
                    GazeData gazeData = (GazeData) data;
                    String file_text=gazeData.getXGaze() + "," + gazeData.getYGaze()+"\n";
                    myOutWriter.append(file_text);
                }
                myOutWriter.flush();
                myOutWriter.close();
                fOut.flush();
                fOut.close();
                System.out.println(i+"번째 끝");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //종료
        httpThread hThread = new httpThread();
        Thread tThread = new Thread(hThread);
        tThread.start();

        finish(); // 지금 화면 종료
    }

    Handler pictureHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            imgLeft.setImageDrawable(leftPic[msg.arg1]);
            imgRight.setImageDrawable(rightPic[msg.arg1]);
            txtWord.setText(textList[msg.arg1]);
            mediaPlayerList.get(msg.arg1).start();

            current_index = msg.arg1;
        }
    };
}
