package com.oscar.galeriawhats.Utilerias;

import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by oemy9 on 02/09/2017.
 */

public class Utils {
    public  static String getUrlGaleria(String ruta){
       return Constants.URL_BASE_GALERIA.concat(ruta);
    }
    public static void shareImage(Context context, Bitmap bitmap){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        ContentValues contentValues=new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,"titulo");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        OutputStream outstream;
        try {
            outstream =context.getContentResolver().openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
            outstream.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, "Share Image"));
    }
    public static void setAsWallpaper(Context context,Bitmap bitmap){
        WallpaperManager wm=WallpaperManager.getInstance(context);
        try {
            wm.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
