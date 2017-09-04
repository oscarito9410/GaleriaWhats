package com.oscar.galeriawhats.IO.Controllers;

import android.content.Context;

import com.oscar.galeriawhats.IO.CallBacks.CallBackCategoria;
import com.oscar.galeriawhats.IO.CallBacks.CallBackImagen;
import com.oscar.galeriawhats.Utilerias.Constants;
import com.oscar.galeriawhats.IO.Model.Response.CategoriaResponse;
import com.oscar.galeriawhats.IO.Model.Response.ImagenResponse;
import com.oscar.galeriawhats.IO.Services.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oemy9 on 22/08/2017.
 */

public class ControllerAPI {
    public static final String TAG="TAG";
    private Context context;
    private Retrofit retrofit;
    private APIService service;

    public ControllerAPI(Context context){
        this.context=context;
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).
                baseUrl(Constants.URL_BASE).build();
        this.service=retrofit.create(APIService.class);
    }
    public void getCategorias(final CallBackCategoria callBackCategoria){
        service.getCategorias(Constants.LISTA).enqueue(new Callback<CategoriaResponse>() {
            @Override
            public void onResponse(Call<CategoriaResponse> call, Response<CategoriaResponse> response) {
                if(response.isSuccessful()){
                    callBackCategoria.OnSuccess(response.body().getCategorias());
                }
                else{
                    callBackCategoria.OnError(new Throwable("Error consulta"));
                }
            }

            @Override
            public void onFailure(Call<CategoriaResponse> call, Throwable t) {
                callBackCategoria.OnError(t);
            }
        });
    }

    public void getUltimos(final CallBackImagen callBackImagen) {
                service.getUltimos(Constants.ULTIMOS).enqueue(new Callback<ImagenResponse>() {
                    @Override
                    public void onResponse(Call<ImagenResponse> call, Response<ImagenResponse> response) {
                        if(response.isSuccessful()){
                            if(!response.body().getListImagenes().isEmpty()){
                                callBackImagen.OnSuccess(response.body());
                            }
                        }
                        else{
                            callBackImagen.OnError(new Throwable("Error al generar consulta"));
                        }
                    }

                    @Override
                    public void onFailure(Call<ImagenResponse> call, Throwable t) {
                                callBackImagen.OnError(t);
                    }
                });
    }
    public void getGaleriaByCategoria(final CallBackImagen callBackImagen,int idCategoria){
        service.getGaleriaByCategoria(Constants.CATEGORIA_PARAM,idCategoria).enqueue(new Callback<ImagenResponse>() {
            @Override
            public void onResponse(Call<ImagenResponse> call, Response<ImagenResponse> response) {
                if(response.isSuccessful()){
                    if(!response.body().getListImagenes().isEmpty()){
                        callBackImagen.OnSuccess(response.body());
                    }
                }
                else{
                    callBackImagen.OnError(new Throwable("Error al generar consulta"));
                }
            }

            @Override
            public void onFailure(Call<ImagenResponse> call, Throwable t) {
                callBackImagen.OnError(t);
            }
        });
    }
}
