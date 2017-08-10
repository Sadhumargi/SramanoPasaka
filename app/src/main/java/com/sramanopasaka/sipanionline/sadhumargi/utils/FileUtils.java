/*
 * Copyright (c) 2016 Techniche E-commerce Solutions Pvt Ltd
 * No.14, 6th Floor,
 * Orchid Techscape, STPI Campus,
 * Cyber Park, Electronics City Phase1,
 * Bangalore-560100.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Techniche E-commerce
 * Solutions Pvt Ltd. You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into with
 * Techniche E-commerce Solutions Pvt Ltd.
 */

package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by techniche on 1/8/16.
 */
public class FileUtils {

  //  public static final String LOGIN_LIST = "login.json";

   /* public static final String INDUSTRY_LIST = "industry.json";

    public static final String DOMAIN_LIST = "domain.json";

    public static final String JOB_LIST = "jobs.json";

    public static final String USER_JOBS_LIST = "user_jobs.json";

    public static final String ARTICLE_LIST = "article.json";

    public static final String ARTICLE_RECOMM_LIST = "article_recomm.json";

    public static final String BOOK_RECOMM_LIST = "book_recomm.json";

    public static final String COURSE_RECOMM_LIST = "course_recomm.json";

    public static final String COURSE_LIST = "course.json";

    public static final String BOOK_LIST = "book.json";

    public static final String MEETING_LIST = "meeting.json";*/


    /**
     * @param context
     * @param contents
     * @param fileName
     * @return
     */
    public static boolean saveFile(Context context, String contents,
                                   String fileName) throws IOException {
        FileOutputStream fos = context.openFileOutput(fileName,
                Context.MODE_PRIVATE);
        Writer out = new OutputStreamWriter(fos);
        out.write(contents);
        out.close();
        return true;

    }


    /**
     * @param context
     * @param fileName
     * @return
     */
    public static String load(Context context, String fileName) {
        if(context!=null) {
            try {
                FileInputStream fis = context.openFileInput(fileName);
                BufferedReader r = new BufferedReader(new InputStreamReader(fis));
                String line = r.readLine();
                r.close();
                return line;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }return null;
    }

    public static boolean isFileExists(Context context, String fileName) {
        if(context!=null) {
            File file = context.getFileStreamPath(fileName);
            if (file.exists())
                return true;
            else
                return false;
        }
        return false;
    }

    public static void deleteFile(Context context, String fileName) {
        if(context!=null) {
            File file = context.getFileStreamPath(fileName);
            if (file.exists())
                file.delete();
        }
    }

    public static void deleteFileFromSdCard(String selectedFilePath) {
        File file = new File(selectedFilePath);
        try {
            file.delete();
        } catch (Exception ex) {

        }
    }

    public static boolean isFileExistsSDCard(String selectedFilePath) {
        File file = new File(selectedFilePath);
        try {
            return file.exists();
        } catch (Exception ex) {
            return false;
        }
    }

    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= 19;

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = (Cursor) context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri
                .getAuthority());
    }

    public static Bitmap decodeFile(String filePth) {
        try {
            File f = new File(filePth);
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE = 1000;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE
                    && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }


    public static String readFileFromAsset(String fileName, Context context) {
        BufferedReader reader = null;
        StringBuffer contents = new StringBuffer();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("dummy/" + fileName)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                contents.append(mLine);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return contents.toString();
    }


    public static String getVideoPathName(String fileName){
        String parent = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath();
        Log.e("Path---", parent + "/valYou/" + fileName);
        return   parent + "/valYou/" + fileName;
    }


    public static boolean renameFile(String source, String destFileName){
        File sdcard = Environment.getExternalStorageDirectory();
        File from = new File(source);
        File to = new File(destFileName);
        return from.renameTo(to);
    }
}

