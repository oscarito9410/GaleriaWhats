package com.oscar.galeriawhats.UI.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.oscar.galeriawhats.IO.Controllers.ControllerAPI;

/**
 * Created by oemy9 on 27/08/2017.
 */

public class BaseAppCompactActivity extends AppCompatActivity {
    protected ControllerAPI controllerAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controllerAPI=new ControllerAPI(this);
    }
}
