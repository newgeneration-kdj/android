package com.android.newgeneration.dandisnap.Profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.newgeneration.dandisnap.R;

/**
 * Created by 천보경 on 2015-07-20.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public int[] mThumbIds = {R.drawable.view01, R.drawable.view02, R.drawable.view03, R.drawable.view04,
            R.drawable.view05, R.drawable.view06, R.drawable.view07, R.drawable.view08,
            R.drawable.view01, R.drawable.view02, R.drawable.view03, R.drawable.view04};
    int rowWidth;

    public ImageAdapter(Context mContext, int rowWidth) {
        this.mContext = mContext;
        this.rowWidth = rowWidth / 3;

    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;
        if (convertView == null) {
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mThumbIds[position]);
            bitmap = Bitmap.createScaledBitmap(bitmap, 320, 320, false);
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
            imageView.setImageBitmap(bitmap);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,ImageActivity.class);
                    intent.putExtra("imageID",mThumbIds[position]);
                    mContext.startActivity(intent);

                }
            });

        } else {
            imageView = (ImageView) convertView;
        }

        return imageView;
    }
}
