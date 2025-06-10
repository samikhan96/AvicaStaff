package com.optimum.AvicaStaff.UI.Doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.PatientFlow.PatientListActivity;

public class RAGActivity extends AppCompatActivity {


    LinearLayout l1,  l3;
    ImageView item_1,item_2,item_3,item_4,item_5,item_6;

    public static String RAG_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rag);


        l1 = findViewById(R.id.l1);
        item_1 = findViewById(R.id.item_1);
        item_2 = findViewById(R.id.item_2);
        item_3 = findViewById(R.id.item_3);
        item_4 = findViewById(R.id.item_4);
        item_5 = findViewById(R.id.item_5);
        item_6 = findViewById(R.id.item_6);
        l3 = findViewById(R.id.l3);


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RAGActivity.this, DashboardActivity.class));
                finish();
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RAGActivity.this, PatientListActivity.class));
                finish();

            }
        });
        item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RAG_type="";
                startActivity(new Intent(RAGActivity.this, RAGAnalaysisActivity.class));
            }
        });

        item_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RAG_type="ECG";
                startActivity(new Intent(RAGActivity.this, RAGAnalaysisActivity.class));
            }
        });

        item_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RAG_type="SPO2";
                startActivity(new Intent(RAGActivity.this, RAGAnalaysisActivity.class));
            }
        });

        item_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RAG_type="BLOODPRESSURE";

                startActivity(new Intent(RAGActivity.this, RAGAnalaysisActivity.class));
            }
        });

        item_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RAG_type="BLOODGLUCOSE";

                startActivity(new Intent(RAGActivity.this, RAGAnalaysisActivity.class));
            }
        });

        item_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RAG_type="TEMPERATURE";

                startActivity(new Intent(RAGActivity.this, RAGAnalaysisActivity.class));
            }
        });

    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}