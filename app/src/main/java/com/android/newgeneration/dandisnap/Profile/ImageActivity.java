package com.android.newgeneration.dandisnap.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.android.newgeneration.dandisnap.R;

public class ImageActivity extends ActionBarActivity {
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mImageView = (ImageView) findViewById(R.id.profile_img_imageview);
        setImage(mImageView);
    }

    private void setImage(ImageView imageView) {
        Intent intent = getIntent();
        int imageID = (int) intent.getExtras().get("imageID");
        imageView.setImageResource(imageID);

    }
}
