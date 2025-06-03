package com.optimum.AvicaStaff;

import android.app.Application;
import android.content.Context;

import com.optimum.AvicaStaff.HttpUtils.HttpRequestHandler;

public class AvicaStaff extends Application {


    public static final String TAG = AvicaStaff.class.getSimpleName();

    private static Context context;
    private static AvicaStaff mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this.getApplicationContext();
        HttpRequestHandler.setAndroidContext(this);

    }

    public static synchronized AvicaStaff getInstance() {
        return mInstance;
    }
    public static Context getContext(){
        return context;
    }


}
