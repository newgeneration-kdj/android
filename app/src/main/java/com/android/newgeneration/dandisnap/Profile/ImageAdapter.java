package com.android.newgeneration.dandisnap.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.newgeneration.dandisnap.R;

import java.lang.ref.WeakReference;

/**
 * Created by 천보경 on 2015-07-20.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public int[] mThumbIds = {R.drawable.view01, R.drawable.view02, R.drawable.view03, R.drawable.view04,
            R.drawable.view05, R.drawable.view06, R.drawable.view07, R.drawable.view08,
            R.drawable.view01, R.drawable.view02, R.drawable.view03, R.drawable.view04};
    int rowWidth;
    int rowHeight;

    public ImageAdapter(Context mContext, int rowWidth, int rowHeight) {
        this.mContext = mContext;
        this.rowWidth = rowWidth;
        this.rowHeight = rowHeight;

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
          /*  Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mThumbIds[position]);
            bitmap = Bitmap.createScaledBitmap(bitmap, 320, 320, false);
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
            imageView.setImageBitmap(bitmap); */


            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
            //imageView.setImageBitmap(bitmap);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ImageActivity.class);
                    intent.putExtra("imageID", mThumbIds[position]);
                    mContext.startActivity(intent);

                }
            });

        } else {
            imageView = (ImageView) convertView;
        }
        int resId = mContext.getResources().getIdentifier(String.valueOf(mThumbIds[position]),
                "drawable", mContext.getPackageName());
        loadBitmap(resId,imageView);

        return imageView;
    }

    public void loadBitmap(int resId, ImageView imageView) {
        BitmapWorkerTask task = new BitmapWorkerTask(imageView);
        task.execute(resId);
    }

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            Bitmap bitmap = decodeSampledBitmapFromResource(mContext.getResources(), data);
            return bitmap;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        public Bitmap decodeSampledBitmapFromResource(Resources res, int resId) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, resId, options);
            int photoW = options.outWidth;
            int photoH = options.outHeight;
            int scaleFactor = Math.min(photoW / 128, photoH / 128);
            Log.d("size", "size : " + scaleFactor);
            options.inSampleSize = scaleFactor;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);

            return bitmap;
        }
    }

}

