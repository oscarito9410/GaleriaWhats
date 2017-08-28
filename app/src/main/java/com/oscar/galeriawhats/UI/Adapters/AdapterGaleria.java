package com.oscar.galeriawhats.UI.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.oscar.galeriawhats.IO.Model.Constants;
import com.oscar.galeriawhats.IO.Model.Response.Imagen;
import com.oscar.galeriawhats.R;
import com.oscar.galeriawhats.UI.ViewHolders.ViewHolderGaleria;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by oemy9 on 27/08/2017.
 */

public class AdapterGaleria extends RecyclerView.Adapter<ViewHolderGaleria> {

    private ArrayList<Imagen>listImagenes;
    private Context context;
    public AdapterGaleria(ArrayList<Imagen>listImagenes){
        this.listImagenes=listImagenes;
    }

    @Override
    public ViewHolderGaleria onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_galeria, parent, false);
        return new ViewHolderGaleria(itemView);
    }
    @Override
    public void onBindViewHolder(final ViewHolderGaleria holder, int position) {
        String url = Constants.URL_BASE_GALERIA.concat(listImagenes.get(position).getRuta());
        Glide.with(context).load(url).placeholder(R.drawable.loading).thumbnail(0.6f).into(holder.imageGaleria);
    }

    @Override
    public int getItemCount() {
        return listImagenes.size();
    }
}
