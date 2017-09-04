package com.oscar.galeriawhats.IO.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by oemy9 on 27/08/2017.
 */
public class Categoria  implements Serializable{
    @SerializedName("id_categoria")
    @Expose
    private String idCategoria;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("portada")
    private String portada;

    @SerializedName("total")
    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}