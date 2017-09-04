package com.oscar.galeriawhats.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oscar.galeriawhats.IO.CallBacks.CallBackCategoria;
import com.oscar.galeriawhats.Utilerias.Constants;
import com.oscar.galeriawhats.IO.Model.Response.Categoria;
import com.oscar.galeriawhats.R;
import com.oscar.galeriawhats.UI.Adapters.AdapterCategoria;
import com.oscar.galeriawhats.UI.CallBacks.ItemClickListener;

import java.util.ArrayList;

public class CategoriasActivity extends BaseAppCompactActivity implements CallBackCategoria,ItemClickListener {

    private RecyclerView recyclerViewCategoria;
    private AdapterCategoria adapterCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        recyclerViewCategoria=(RecyclerView)findViewById(R.id.recyclerViewCategorias);
        controllerAPI.getCategorias(this);
    }

    @Override
    public void OnSuccess(ArrayList<Categoria> listCategorias) {
            if(!listCategorias.isEmpty()){
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
                adapterCategoria=new AdapterCategoria(this,listCategorias);
                recyclerViewCategoria.setLayoutManager(layoutManager);
                recyclerViewCategoria.setAdapter(adapterCategoria);
                recyclerViewCategoria.setHasFixedSize(true);
                adapterCategoria.setItemClickListener(this);
            }
    }
    @Override
    public void OnError(Throwable error) {

    }

    @Override
    public void onItemClickListener(int position) {
        Bundle bundle=new Bundle();
        bundle.putSerializable(Constants.SELECTED_CATEGORIA,adapterCategoria.getItem(position));
        Intent intent=new Intent(this,GaleriaActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
