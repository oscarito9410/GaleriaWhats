package com.oscar.galeriawhats.UI.Activities;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.oscar.galeriawhats.Utilerias.Constants;
import com.oscar.galeriawhats.IO.Model.Response.Imagen;
import com.oscar.galeriawhats.R;
import com.oscar.galeriawhats.Utilerias.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class DetalleActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private Bitmap  currentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        final ImageView imageDetalle=(ImageView)findViewById(R.id.imagenDetalle);

        Imagen selectedImagen=(Imagen)getIntent().getSerializableExtra(Constants.SELECTED_IMAGEN);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            String imagenTransition = getIntent().getStringExtra(Constants.SELECTED_IMAGE_TRANSITION);
            imageDetalle.setTransitionName(imagenTransition);
            com.squareup.picasso.Target target = new com.squareup.picasso.Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    currentBitmap = bitmap;
                    imageDetalle.setImageBitmap(currentBitmap);
                    supportStartPostponedEnterTransition();
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    supportStartPostponedEnterTransition();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    supportStartPostponedEnterTransition();
                }
            };
            Picasso.with(this).load(Utils.getUrlGaleria(selectedImagen.getRuta())).into(target);
            imageDetalle.setTag(target);
        }
    }
    public void onCompartir(View v){
        shareImagenGaleria();
    }
    public void onWallpaper(View v){
        setAsWallpaper();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @AfterPermissionGranted(Constants.PERMISO_REQUEST_WRITE_EXTERNAL)
    private void shareImagenGaleria(){
        if(EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Utils.shareImage(this,currentBitmap);
        }
        else{
            EasyPermissions.requestPermissions(this,"Permiso",
                    Constants.PERMISO_REQUEST_WRITE_EXTERNAL,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }
    @AfterPermissionGranted(Constants.PERMISO_REQUEST_WALLPAPER)
    private void setAsWallpaper(){
        if(EasyPermissions.hasPermissions(this,Manifest.permission.SET_WALLPAPER)){
            Utils.setAsWallpaper(this,currentBitmap);
        }
        else{
            EasyPermissions.requestPermissions(this,"Permiso",
                    Constants.PERMISO_REQUEST_WRITE_EXTERNAL,Manifest.permission.SET_WALLPAPER);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(requestCode==Constants.PERMISO_REQUEST_WRITE_EXTERNAL){
            Utils.shareImage(this,currentBitmap);
        }
        else if(requestCode==Constants.PERMISO_REQUEST_WALLPAPER){
            Utils.setAsWallpaper(this,currentBitmap);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
