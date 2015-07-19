package com.android.newgeneration.dandisnap.Camera;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
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
    static final String PICFOLDER = "/DCIM/Camera";
    @InjectView(R.id.camera_btn_shutter)
    Button mCameraBtnShutter;
    @InjectView(R.id.camera_btn_close)
    Button mCameraBtnClose;
    @InjectView(R.id.camera_previewFrame)
    MyCameraSurface mMyCameraSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ButterKnife.inject(this);

        mRootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + PICFOLDER;

        File fRoot = new File(mRootPath);
        if (fRoot.exists() == false) {
            if (fRoot.mkdir() == false) {
                Toast.makeText(this, R.string.no_folder, Toast.LENGTH_SHORT).show();
                finish();

                return;
            }
        }
    }

    @OnClick({R.id.camera_btn_shutter, R.id.camera_btn_close})
    void onButtonClick(Button btn) {
        switch (btn.getId()) {
            case R.id.camera_btn_shutter:
                mMyCameraSurface.mCamera.autoFocus(mAutoFocus);
                break;
            case R.id.camera_btn_close:
                finish();
                break;
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

            Intent intent2 = new Intent(getApplicationContext(),ActivityPicture.class);
            intent2.putExtra("path",path);
            startActivity(intent2);

        }
    };


}

