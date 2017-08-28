package com.oscar.galeriawhats.IO.Services;

import com.oscar.galeriawhats.IO.Model.Constants;
import com.oscar.galeriawhats.IO.Model.Response.CategoriaResponse;
import com.oscar.galeriawhats.IO.Model.Response.Imagen;
import com.oscar.galeriawhats.IO.Model.Response.ImagenResponse;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by oemy9 on 24/08/2017.
 */

public interface APIService {
    @GET(Constants.CATEGORIAS)
    Call<CategoriaResponse> getCategorias(@Query(Constants.PARAM) String param);
    @GET(Constants.GALERIA)
    Call<ImagenResponse> getUltimos(@Query(Constants.PARAM) String param);
    @GET(Constants.GALERIA)
    Call<ImagenResponse>getGaleriaByCategoria(@Query(Constants.PARAM) String param, @Query(Constants.ID_PARAM) int idCategoria);
}
