package com.optimum.AvicaStaff.UI.Doctor.Respiratory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.optimum.AvicaStaff.R;

public class RespiratoryActivity extends AppCompatActivity {


    ImageView item_1,item_2,item_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiratory);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        item_1 = findViewById(R.id.item_1);
        item_2 = findViewById(R.id.item_2);
        item_3 = findViewById(R.id.item_3);


        item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RespiratoryActivity.this, AutomaticActivity.class));
            }
        });

        item_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RespiratoryActivity.this, ManualActivity.class));
            }
        });

        item_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RespiratoryActivity.this, WatchTutorialActivity.class));
            }
        });
    }
}