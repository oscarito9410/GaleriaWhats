package com.oscar.galeriawhats.UI.Activities;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.oscar.galeriawhats.IO.CallBacks.CallBackImagen;
import com.oscar.galeriawhats.IO.Model.Constants;
import com.oscar.galeriawhats.IO.Model.Response.Categoria;
import com.oscar.galeriawhats.IO.Model.Response.ImagenResponse;
import com.oscar.galeriawhats.R;
import com.oscar.galeriawhats.UI.Adapters.AdapterGaleria;

public class GaleriaActivity extends BaseAppCompactActivity implements CallBackImagen {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
                    AdapterGaleria adapterGaleria=new AdapterGaleria(response.getListImagenes());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterGaleria);
                    recyclerView.setHasFixedSize(true);
                }

    }
    @Override
    public void OnError(Throwable error) {
    }

}
