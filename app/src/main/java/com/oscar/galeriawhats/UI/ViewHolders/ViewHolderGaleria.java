package com.oscar.galeriawhats.UI.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.oscar.galeriawhats.R;

/**
 * Created by oemy9 on 27/08/2017.
 */

public class ViewHolderGaleria extends RecyclerView.ViewHolder{

    public ShimmerFrameLayout shimmerLayout;
    public ImageView imageGaleria;

    public ViewHolderGaleria(View itemView) {
        super(itemView);
        shimmerLayout=(ShimmerFrameLayout)itemView.findViewById(R.id.shimmeerFrameLayout);
        imageGaleria=(ImageView)itemView.findViewById(R.id.imageGaleria);
    }

    public ImageView getImageGaleria() {
        return imageGaleria;
    }
}
