package com.android.newgeneration.dandisnap.Camera;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.android.newgeneration.dandisnap.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityCamera extends Activity {

    String mRootPath;
    public static final int StatusPicture = 0;
    public static final int StatusGallery = 1;
    static final String PICFOLDER = "/DCIM/Camera";
    @InjectView(R.id.camera_btn_shutter)
    Button mCameraBtnShutter;
    @InjectView(R.id.camera_btn_picture_close)
    Button mCameraBtnPictureClose;
    @InjectView(R.id.camera_btn_gallery_close)
    Button mCameraBtnGalleryClose;
    @InjectView(R.id.camera_btn_gallery)
    Button mCameraBtnGallery;
    @InjectView(R.id.camera_btn_picture)
    Button mCameraBtnPicture;
    @InjectView(R.id.camera_fl_gallery)
    FrameLayout mCameraFlGallery;
    @InjectView(R.id.camera_fl_picture)
    FrameLayout mCameraFlPicture;
    @InjectView(R.id.camera_previewFrame)
    MyCameraSurface mMyCameraSurface;
    @InjectView((R.id.camera_grv_gallery))
    GridView mCameraGrvGallery;
    ImageAdapter mImageAdapter;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mImageAdapter = new ImageAdapter(this);
        ButterKnife.inject(this);

        mRootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + PICFOLDER;
        FolderChecked();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        int status = mSharedPreferences.getInt("status", StatusPicture);
        if (status == StatusPicture)
            mCameraBtnPicture.performClick();
        else if (status == StatusGallery)
            mCameraBtnGallery.performClick();
    }

    @OnClick({R.id.camera_btn_shutter, R.id.camera_btn_picture_close, R.id.camera_btn_gallery, R.id.camera_btn_picture, R.id.camera_btn_gallery_close})
    void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.camera_btn_gallery:
                mCameraFlGallery.setVisibility(View.VISIBLE);
                mCameraFlPicture.setVisibility(View.GONE);
                mCameraGrvGallery.setAdapter(mImageAdapter);
                mCameraGrvGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        mImageAdapter.callImageViewer(position);
                    }
                });
                SaveStatus(StatusGallery);
                break;
            case R.id.camera_btn_picture:
                mCameraFlGallery.setVisibility(View.GONE);
                mCameraFlPicture.setVisibility(View.VISIBLE);
                SaveStatus(StatusPicture);
                break;
            case R.id.camera_btn_shutter:
                mMyCameraSurface.mCamera.autoFocus(mAutoFocus);
                break;
            case R.id.camera_btn_picture_close:
                finish();
                break;
            case R.id.camera_btn_gallery_close:
                finish();
                break;
        }

    }

    void SaveStatus(int status) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove("status");
        editor.putInt("status", status);
        editor.commit();
    }

    void FolderChecked() {
        File fRoot = new File(mRootPath);
        if (fRoot.exists() == false) {
            if (fRoot.mkdir() == false) {
                Toast.makeText(this, R.string.no_folder, Toast.LENGTH_SHORT).show();
                finish();

                return;
            }
        }
    }


    Camera.AutoFocusCallback mAutoFocus = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            mCameraBtnShutter.setEnabled(success);
            mMyCameraSurface.mCamera.takePicture(null, null, mPicture);

        }
    };

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {

            Calendar calendar = Calendar.getInstance();
            String FileName = String.format("IMG_%02d%02d%02d_%02d%02d%02d.jpg",
                    calendar.get(Calendar.YEAR) % 100, calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
            String path;
            path = mRootPath + "/" + FileName;

            File file = new File(path);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
                fos.close();

            } catch (Exception e) {

                return;
            }

            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.parse("file://" + path);
            intent.setData(uri);
            sendBroadcast(intent);

            Toast.makeText(getApplicationContext(), R.string.save_completed, Toast.LENGTH_SHORT).show();

            camera.stopPreview();

            Intent intentPicture = new Intent(getApplicationContext(), ActivitySharing.class);
            intentPicture.putExtra("path", path);
            intentPicture.putExtra("status", StatusPicture);
            startActivity(intentPicture);

        }
    };


}

