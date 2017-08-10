package com.sramanopasaka.sipanionline.sadhumargi.helpers;

import android.content.Context;
import android.os.AsyncTask;

import com.sramanopasaka.sipanionline.sadhumargi.model.ProfileImage;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ImageUtil;

public class ImageCompressionAsyncTask extends AsyncTask<String, Void, ProfileImage> {
    public interface OnImageCompressed {
        void onCompressedImage(ProfileImage bitmap);
    }

    private Context mContext;
    private int mWidth;
    private int mHeight;
    private OnImageCompressed mOnImageCompressed;

    public ImageCompressionAsyncTask(Context context, int width, int height) {
        this.mContext = context;
        mWidth = width;
        mHeight = height;
    }

    public void setOnImageCompressed(OnImageCompressed listener) {
        this.mOnImageCompressed = listener;
    }


    @Override
    protected ProfileImage doInBackground(String... params) {
        ProfileImage profileImage = new ProfileImage();
        String filePath = new ImageUtil(mContext).compressImage(params[0], mWidth, mHeight);
        profileImage.image = ImageUtil.decodeBitmapFromPath(params[0]);
        profileImage.imageString = ImageUtil.encodeTobase64(profileImage.image);
        return profileImage;
    }

    @Override
    protected void onPostExecute(ProfileImage result) {
        super.onPostExecute(result);
        if (mOnImageCompressed != null) {
            mOnImageCompressed.onCompressedImage(result);
        }
    }

}