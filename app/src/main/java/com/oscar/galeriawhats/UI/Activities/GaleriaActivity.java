package com.oscar.galeriawhats.UI.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.oscar.galeriawhats.IO.CallBacks.CallBackImagen;
import com.oscar.galeriawhats.Utilerias.Constants;
import com.oscar.galeriawhats.IO.Model.Response.Categoria;
import com.oscar.galeriawhats.IO.Model.Response.ImagenResponse;
import com.oscar.galeriawhats.R;
import com.oscar.galeriawhats.UI.Adapters.AdapterGaleria;
import com.oscar.galeriawhats.UI.CallBacks.ItemClickListenerTransition;

import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

public class GaleriaActivity extends BaseAppCompactActivity implements CallBackImagen, ItemClickListenerTransition {
    private RecyclerView recyclerView;
    private AdapterGaleria adapterGaleria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        if(getIntent().hasExtra(Constants.SELECTED_CATEGORIA)){
            Categoria selectedCategoria=(Categoria)
                    getIntent().getSerializableExtra(Constants.SELECTED_CATEGORIA);

            controllerAPI.getGaleriaByCategoria(this,
                    Integer.valueOf(selectedCategoria.getIdCategoria()));

            if(getSupportActionBar()!=null){
                getSupportActionBar().setTitle(selectedCategoria.getNombre());
            }
        }


    }

    @Override
    public void OnSuccess(ImagenResponse response) {
                if(!response.getListImagenes().isEmpty()){
                    GridLayoutManager layoutManager = new GridLayoutManager(this,2);
                    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if(position%5==0){
                                return  2;
                            }
                            return 1;
                        }
                    });
                    adapterGaleria=new AdapterGaleria(this,response.getListImagenes());
                    adapterGaleria.setItemClickListenerTransition(this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new SlideInBottomAnimationAdapter(adapterGaleria));
                    recyclerView.setHasFixedSize(true);
                }

    }
    @Override
    public void OnError(Throwable error) {
    }


    @Override
    public void onItemClickListener(int position, ImageView sharedImageView) {
        Intent intent=new Intent(this,DetalleActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(Constants.SELECTED_IMAGEN,adapterGaleria.getItem(position));
        bundle.putString(Constants.SELECTED_IMAGE_TRANSITION, ViewCompat.getTransitionName(sharedImageView));
        intent.putExtras(bundle);
        ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(this,sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));
        startActivity(intent,options.toBundle());
    }
}
