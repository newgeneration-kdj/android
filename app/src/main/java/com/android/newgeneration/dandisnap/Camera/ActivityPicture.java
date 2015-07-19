package com.android.newgeneration.dandisnap.Camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.android.newgeneration.dandisnap.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ActivityPicture extends Activity {

    @InjectView(R.id.picture_btn_backsign)
    Button mPictureBtnBacksign;
    @InjectView(R.id.picture_btn_share)
    Button mPIctureBtnShare;
    @InjectView(R.id.picture_img_picture)
    ImageView mPictureImgPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        ButterKnife.inject(this);

        String path = getIntent().getExtras().getString("path");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bm = BitmapFactory.decodeFile(path, options);
        bm = imgResized(bm);
        mPictureImgPicture.setImageBitmap(bm);
    }

    Bitmap imgResized(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        Matrix matrix = new Matrix();
        matrix.postRotate(90);

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
