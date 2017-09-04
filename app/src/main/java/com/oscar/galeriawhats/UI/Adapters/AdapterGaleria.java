package com.oscar.galeriawhats.UI.Adapters;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.oscar.galeriawhats.IO.Model.Response.Imagen;
import com.oscar.galeriawhats.R;
import com.oscar.galeriawhats.UI.CallBacks.ItemClickListener;
import com.oscar.galeriawhats.UI.CallBacks.ItemClickListenerTransition;
import com.oscar.galeriawhats.UI.ViewHolders.ViewHolderGaleria;
import com.oscar.galeriawhats.Utilerias.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by oemy9 on 27/08/2017.
 * https://proandroiddev.com/enter-animation-using-recyclerview-and-layoutanimation-part-1-list-75a874a5d213
 */

public class AdapterGaleria extends RecyclerView.Adapter<ViewHolderGaleria> implements IAdapterBase {


    public static final int FULL_TYPE=1;
    public static final int CONTENT_TYPE=1;

    private ItemClickListener itemClickListener;
    private ItemClickListenerTransition itemClickListenerTransition;

    private ArrayList<Imagen>listImagenes;
    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterGaleria(Context context, ArrayList<Imagen>listImagenes){
        this.context=context;
        this.listImagenes=listImagenes;
        Collections.shuffle(this.listImagenes);
        this.layoutInflater=LayoutInflater.from(this.context);
    }

    @Override
    public int getItemViewType(int position) {
        if(position%5==0){
            return  FULL_TYPE;
        }
        return CONTENT_TYPE;
    }

    @Override
    public ViewHolderGaleria onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType==FULL_TYPE){
            itemView=layoutInflater.inflate(R.layout.item_galeria_full,parent,false);
        }
        else{
            itemView =layoutInflater.inflate(R.layout.item_galeria, parent, false);
        }
        return new ViewHolderGaleria(itemView);
    }
    @Override
    public void onBindViewHolder(final ViewHolderGaleria holder, final int position) {
        String url = Utils.getUrlGaleria(listImagenes.get(position).getRuta());
        holder.shimmerLayout.startShimmerAnimation();
        ViewCompat.setTransitionName(holder.imageGaleria,context.getString(R.string.transition_imagen));

        com.squareup.picasso.Target target=new com.squareup.picasso.Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
             //   holder.imageGaleria.setScaleType(ImageView.ScaleType.FIT_XY);
                holder.imageGaleria.setImageBitmap(bitmap);
                holder.shimmerLayout.stopShimmerAnimation();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                holder.imageGaleria.setScaleType(ImageView.ScaleType.CENTER_CROP);
                holder.imageGaleria.setImageDrawable(placeHolderDrawable);
            }
        };
        Picasso.with(this.context).load(url).placeholder(R.drawable.loading).resize(300,300).centerInside().into(target);
        holder.imageGaleria.setTag(target);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListenerTransition.onItemClickListener(position,holder.imageGaleria);
            }
        });

        /*
        Glide.with(context).load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                holder.shimmerLayout.stopShimmerAnimation();
                return false;
            }
        }).thumbnail(0.4f).into(holder.imageGaleria);
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 itemClickListenerTransition.onItemClickListener(position,holder.imageGaleria);
             }
         });*/

    }

    @Override
    public int getItemCount() {
        return listImagenes.size();
    }

    @Override
    public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener=itemClickListener;
    }
    public void setItemClickListenerTransition(ItemClickListenerTransition itemClickListenerTransition){
        this.itemClickListenerTransition=itemClickListenerTransition;
    }
    @Override
    public Imagen getItem(int position) {
        return listImagenes.get(position);
    }
}
