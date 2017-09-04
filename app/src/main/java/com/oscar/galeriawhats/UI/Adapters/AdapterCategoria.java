package com.oscar.galeriawhats.UI.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscar.galeriawhats.IO.Model.Response.Categoria;
import com.oscar.galeriawhats.R;
import com.oscar.galeriawhats.UI.CallBacks.ItemClickListener;
import com.oscar.galeriawhats.UI.ViewHolders.ViewHoderCategoria;
import com.oscar.galeriawhats.Utilerias.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by oemy9 on 27/08/2017.
 */

public class AdapterCategoria extends RecyclerView.Adapter<ViewHoderCategoria> implements IAdapterBase {

    private ItemClickListener clickListener;
    private ArrayList<Categoria>listCategorias;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterCategoria(Context context, ArrayList<Categoria>listCategorias){
        this.listCategorias=listCategorias;
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    public void setItemClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHoderCategoria onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_categoria, parent, false);
        return new ViewHoderCategoria(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHoderCategoria holder, final int position) {
        Categoria selectedCategoria=getItem(position);
        holder.tvCategoria.setText(String.format("%s (%s)",selectedCategoria.getNombre(),selectedCategoria.getTotal()));
        if(selectedCategoria.getPortada()!=null){
            Picasso.with(context).load(Utils.getUrlGaleria(selectedCategoria.getPortada()))
                    .into(holder.imageCategoria);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener!=null){
                    clickListener.onItemClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCategorias.size();
    }
    public Categoria getItem(int position){
       return listCategorias.get(position);
    }
}
