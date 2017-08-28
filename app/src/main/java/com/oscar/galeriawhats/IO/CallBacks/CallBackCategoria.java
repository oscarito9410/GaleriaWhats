package com.oscar.galeriawhats.IO.CallBacks;

import com.oscar.galeriawhats.IO.Model.Response.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oemy9 on 27/08/2017.
 */

public interface CallBackCategoria  extends CallBackBase{
    void OnSuccess(ArrayList<Categoria> listCategorias);
}
