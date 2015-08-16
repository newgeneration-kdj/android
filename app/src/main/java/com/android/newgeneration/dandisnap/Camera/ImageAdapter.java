package com.android.newgeneration.dandisnap.Camera;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.util.LruCache;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by ÁÖ½ÂÈ¯ on 2015-07-27.
 */
public class ImageAdapter extends BaseAdapter {
    String imgData;
    String geoData;
    ArrayList<String> thumbsDataList;
    ArrayList<String> thumbsIDList;
    Context mContext;
    int screenWidth;
    LruCache<String, Bitmap> mMemoryCache;

    ImageAdapter(Context context) {
        mContext = context;
        thumbsDataList = new ArrayList<String>();
        thumbsIDList = new ArrayList<String>();
        getThumbInfo(thumbsIDList, thumbsDataList);
        Display dis = ((WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE)).getDefaultDisplay();
        screenWidth = dis.getWidth();
        final int memClass
                = ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE))
                .getMemoryClass();

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = 1024 * 1024 * memClass / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in bytes rather than number of items.
                return bitmap.getByteCount();
            }
        };
    }

    public final void callImageViewer(int selectedIndex) {
        Intent intentGallery = new Intent(mContext, ActivitySharing.class);
        String imgPath = getImageInfo(imgData, geoData, thumbsIDList.get(selectedIndex));
        intentGallery.putExtra("path", imgPath);
        intentGallery.putExtra("status", ActivityCamera.StatusGallery);
        mContext.startActivity(intentGallery);
    }

    public boolean deleteSelected(int Index) {
        return true;
    }

    public int getCount() {
        return thumbsIDList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(screenWidth / 4, screenWidth / 4));
            imageView.setAdjustViewBounds(false);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }
        //Bitmap bmp = decodeSampledBitmapFromUri(thumbsDataList.get(position), screenWidth / 4, screenWidth / 4);

        /*BitmapFactory.Options bo = new BitmapFactory.Options();
        bo.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(thumbsDataList.get(position), bo);
        int photoW = bo.outWidth;
        int photoH = bo.outHeight;
        int scaleFactor = Math.min(photoW / 128, photoH / 128);
        bo.inSampleSize = scaleFactor;
        bo.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeFile(thumbsDataList.get(position), bo);*/
        final String imageKey = thumbsDataList.get(position);
        final Bitmap bmp = getBitmapFromMemCache(imageKey);

        if (bmp == null) {
            BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            task.execute(imageKey);
        }

        imageView.setImageBitmap(bmp);
        return imageView;
    }

    public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {

        Bitmap bmp = null;
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeFile(path, options);

        return bmp;
    }

    public int calculateInSampleSize(

            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }

        return inSampleSize;
    }

    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

        private final WeakReference<ImageView> imageViewReference;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            final Bitmap bitmap = decodeSampledBitmapFromUri(params[0], screenWidth / 4, screenWidth / 4);
            addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }


    private void getThumbInfo(ArrayList<String> thumbsIDs, ArrayList<String> thumbsDatas) {
        String[] proj = {MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE};

        Cursor imageCursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                proj, null, null, null);

        if (imageCursor != null && imageCursor.moveToFirst()) {
            String title;
            String thumbsID;
            String thumbsImageID;
            String thumbsData;
            String data;
            String imgSize;

            int thumbsIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
            int thumbsDataCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int thumbsImageIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
            int thumbsSizeCol = imageCursor.getColumnIndex(MediaStore.Images.Media.SIZE);
            int num = 0;
            do {
                thumbsID = imageCursor.getString(thumbsIDCol);
                thumbsData = imageCursor.getString(thumbsDataCol);
                thumbsImageID = imageCursor.getString(thumbsImageIDCol);
                imgSize = imageCursor.getString(thumbsSizeCol);
                num++;
                if (thumbsImageID != null) {
                    thumbsIDs.add(thumbsID);
                    thumbsDatas.add(thumbsData);
                }
            } while (imageCursor.moveToNext());
        }
        imageCursor.close();
        return;
    }

    private String getImageInfo(String ImageData, String Location, String thumbID) {
        String imageDataPath = null;
        String[] proj = {MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE};
        Cursor imageCursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                proj, "_ID='" + thumbID + "'", null, null);

        if (imageCursor != null && imageCursor.moveToFirst()) {
            if (imageCursor.getCount() > 0) {
                int imgData = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                imageDataPath = imageCursor.getString(imgData);
            }
        }
        imageCursor.close();
        return imageDataPath;
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}

