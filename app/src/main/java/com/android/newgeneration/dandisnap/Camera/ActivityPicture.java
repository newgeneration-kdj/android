package com.android.newgeneration.dandisnap.Camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.newgeneration.dandisnap.R;

public class ActivityPicture extends Activity {

    ImageView mPictureImgPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        mPictureImgPicture = (ImageView) findViewById(R.id.picture_img_picture);

        String path = getIntent().getExtras().getString("path");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bm = BitmapFactory.decodeFile(path, options);
        bm = imgResized(bm);
        mPictureImgPicture.setImageBitmap(bm);
    }

    Bitmap imgResized(Bitmap bmp)
    {
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        Bitmap rotatedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);
        Bitmap resizedBitmap = Bitmap.createBitmap(rotatedBitmap, 0, rotatedBitmap.getHeight()*1/11, rotatedBitmap.getWidth(), rotatedBitmap.getWidth());
        bmp.recycle();

        return resizedBitmap;
    }


}
