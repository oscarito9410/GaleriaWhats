package com.oscar.galeriawhats.IO.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oemy9 on 27/08/2017.
 */
public class CategoriaResponse {
    @SerializedName("categorias")
    @Expose
    private ArrayList<Categoria> listCategorias;

    public ArrayList<Categoria> getCategorias() {
        return listCategorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.listCategorias = categorias;
    }
}
