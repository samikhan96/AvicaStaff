package com.optimum.AvicaStaff.UI.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.optimum.AvicaStaff.HttpUtils.ConfigConstants;
import com.optimum.AvicaStaff.Models.User;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.Utils.UserPrefs;

public class SplashActivity extends AppCompatActivity {

    Handler handler;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        user = UserPrefs.getGetUser();


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user!=null) {
                    ConfigConstants.token = user.token;
                    startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                }
                else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }


            }
        }, 3000);

    }
}