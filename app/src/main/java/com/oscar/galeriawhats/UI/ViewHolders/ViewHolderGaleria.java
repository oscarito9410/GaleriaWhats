package com.oscar.galeriawhats.UI.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.oscar.galeriawhats.R;

/**
 * Created by oemy9 on 27/08/2017.
 */

public class ViewHolderGaleria extends RecyclerView.ViewHolder{

    public ImageView imageGaleria;

    public ViewHolderGaleria(View itemView) {
        super(itemView);
        imageGaleria=(ImageView)itemView.findViewById(R.id.imageGaleria);
    }

    public ImageView getImageGaleria() {
        return imageGaleria;
    }
}
