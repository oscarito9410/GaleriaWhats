package com.oscar.galeriawhats.IO.CallBacks;

import com.oscar.galeriawhats.IO.Model.Response.ImagenResponse;

/**
 * Created by oemy9 on 27/08/2017.
 */

public interface CallBackImagen extends CallBackBase {
    void OnSuccess(ImagenResponse response);
}
