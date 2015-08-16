package com.android.newgeneration.dandisnap.Camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.android.newgeneration.dandisnap.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ActivitySharing extends Activity {

    @InjectView(R.id.picture_btn_backsign)
    Button mPictureBtnBacksign;
    @InjectView(R.id.picture_btn_share)
    Button mPIctureBtnShare;
    @InjectView(R.id.picture_img_picture)
    ImageView mPictureImgPicture;
    public static final int imgWidth = 400;
    public static final int imgHeight = 400;
    int status, mCameraFacing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);

        ButterKnife.inject(this);
        StatusChecked();
    }

    void StatusChecked() {
        status = getIntent().getExtras().getInt("status");
        mCameraFacing = getIntent().getExtras().getInt("front_back");
        if (status == ActivityCamera.StatusPicture) {
            String path = getIntent().getExtras().getString("path");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bm = BitmapFactory.decodeFile(path, options);
            bm = imgResized(bm);
            mPictureImgPicture.setImageBitmap(bm);
        } else if (status == ActivityCamera.StatusGallery) {
            String path = getIntent().getExtras().getString("path");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bm = BitmapFactory.decodeFile(path, options);
            bm = Bitmap.createScaledBitmap(bm, imgWidth, imgHeight, true);
            mPictureImgPicture.setImageBitmap(bm);
        }
    }

    Bitmap imgResized(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        Matrix matrix = new Matrix();
        if (mCameraFacing == Camera.CameraInfo.CAMERA_FACING_BACK)
            matrix.postRotate(90);
        else {
            matrix.postRotate(270);
            matrix.postScale(-1, 1);
        }

        Bitmap rotatedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);
        Bitmap resizedBitmap = Bitmap.createBitmap(rotatedBitmap, 0, rotatedBitmap.getHeight() * 1 / 11, rotatedBitmap.getWidth(), rotatedBitmap.getWidth());
        bmp.recycle();

        return resizedBitmap;
    }

    @OnClick({R.id.picture_btn_share, R.id.picture_btn_backsign})
    void onButtonClick(Button btn) {
        switch (btn.getId()) {
            case R.id.picture_btn_share:
                break;
            case R.id.picture_btn_backsign:
                onBackPressed();
                break;

        }
    }


}
