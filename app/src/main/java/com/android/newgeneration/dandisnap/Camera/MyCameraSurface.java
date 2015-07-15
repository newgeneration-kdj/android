package com.android.newgeneration.dandisnap.Camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

/**
 * Created by 주승환 on 2015-07-14.
 */
class MyCameraSurface extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder mHolder;
    Camera mCamera;

    public MyCameraSurface(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    public void surfaceCreated(SurfaceHolder holder) {

        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            mCamera.release();
            mCamera = null;
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {

        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Camera.Parameters parameters = mCamera.getParameters();
        Camera.Size size =  getOptimalPictureSize(parameters.getSupportedPictureSizes(), 2592, 1944);
        parameters.setPreviewSize(size.width, size.height);
        parameters.setPictureSize(size.width, size.height);
        parameters.setRotation(90);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

    private Camera.Size getOptimalPictureSize(List<Camera.Size> sizeList, int width, int height){

        Camera.Size prevSize = sizeList.get(0);
        Camera.Size optSize = sizeList.get(1);
        for(Camera.Size size : sizeList){

            int diffWidth = Math.abs((size.width - width));
            int diffHeight = Math.abs((size.height - height));

            int diffWidthPrev = Math.abs((prevSize.width - width));
            int diffHeightPrev = Math.abs((prevSize.height - height));

            int diffWidthOpt = Math.abs((optSize.width - width));
            int diffHeightOpt = Math.abs((optSize.height - height));

            if(diffWidth < diffWidthPrev && diffHeight <= diffHeightOpt){
                optSize = size;

            }

            if(diffHeight < diffHeightPrev && diffWidth <= diffWidthOpt){
                optSize = size;

            }

            prevSize = size;
        }

        return optSize;
    }

}
