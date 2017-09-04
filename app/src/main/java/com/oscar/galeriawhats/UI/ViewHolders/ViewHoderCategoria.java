package com.oscar.galeriawhats.UI.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oscar.galeriawhats.R;

/**
 * Created by oemy9 on 27/08/2017.
 */

public class ViewHoderCategoria extends RecyclerView.ViewHolder {
    public ImageView imageCategoria;
    public TextView tvCategoria;
    public ViewHoderCategoria(View itemView) {
        super(itemView);
        tvCategoria=(TextView)itemView.findViewById(R.id.tvCategoria);
        imageCategoria=(ImageView)itemView.findViewById(R.id.imageCategoria);
    }
}
