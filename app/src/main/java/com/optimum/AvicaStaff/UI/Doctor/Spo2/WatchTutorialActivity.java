package com.optimum.AvicaStaff.UI.Doctor.Spo2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.Tutorials;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.Utils.AppUtils;

public class WatchTutorialActivity extends AppCompatActivity {

    Tutorials tutorials;
    TextView tv_1,tv_2;
    String youtubeUrl;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg_watch_tutorial);
        category="SPO2";
        ImageView back = findViewById(R.id.back);
        tv_1  = findViewById(R.id.tv_1);
        tv_2  = findViewById(R.id.tv_2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getTutorials();
        ImageView play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                intent.setPackage("com.google.android.youtube"); // Force to open in YouTube app if installed

                try {
                    startActivity(intent); // Try to open in YouTube app
                } catch (ActivityNotFoundException e) {
                    // If YouTube app is not installed, open in browser
                    intent.setPackage(null);
                    startActivity(intent);
                }

            }
        });

    }

    public void getTutorials(){
        AppUtils.showProgressDialog(WatchTutorialActivity.this);

        AppServices.getTutorials(WatchTutorialActivity.class.getSimpleName(),category, new ServiceListener<Tutorials, String>() {
            @Override
            public void success(Tutorials success) {
                tutorials=success;
                tv_1.setText(tutorials.title);
                tv_2.setText(tutorials.description);
                youtubeUrl = tutorials.uri; // your video URL
                AppUtils.dismisProgressDialog(WatchTutorialActivity.this);

            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(WatchTutorialActivity.this);

            }
        });
    }
}