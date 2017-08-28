package com.oscar.galeriawhats.IO.Model.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by oemy9 on 27/08/2017.
 */

public class ImagenResponse {
    @SerializedName("imagenes")
    private ArrayList<Imagen>listImagenes;

    public ArrayList<Imagen> getListImagenes() {
        return listImagenes;
    }

    public void setListImagenes(ArrayList<Imagen> listImagenes) {
        this.listImagenes = listImagenes;
    }
}
