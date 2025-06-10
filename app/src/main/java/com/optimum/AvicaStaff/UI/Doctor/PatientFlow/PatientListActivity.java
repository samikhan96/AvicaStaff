package com.optimum.AvicaStaff.UI.Doctor.PatientFlow;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.optimum.AvicaStaff.Adapters.AdapterPatientList;
import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.PatientList;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.DashboardActivity;
import com.optimum.AvicaStaff.UI.Doctor.RAGActivity;
import com.optimum.AvicaStaff.Utils.AppUtils;

import java.util.ArrayList;

public class PatientListActivity extends AppCompatActivity {

    RecyclerView list1;
    ArrayList<PatientList> patientListArrayList = new ArrayList<>();
    AdapterPatientList adapterPatientList;
    LinearLayout l1,l2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientlist);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        list1=findViewById(R.id.list1);
        getPatientProfile();

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientListActivity.this, DashboardActivity.class));
                finish();
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientListActivity.this, RAGActivity.class));
                finish();
            }
        });
    }

    public void getPatientProfile(){
        AppUtils.showProgressDialog(PatientListActivity.this);

        AppServices.getPatientList(PatientListActivity.class.getSimpleName(), new ServiceListener<ArrayList<PatientList>, String>() {
            @Override
            public void success(ArrayList<PatientList> success) {
                AppUtils.dismisProgressDialog(PatientListActivity.this);
                patientListArrayList = success;
                setAdapter();

            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(PatientListActivity.this);

            }
        });
    }
    public void setAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list1.setLayoutManager(layoutManager);
        adapterPatientList = new AdapterPatientList(PatientListActivity.this, patientListArrayList, this);
        list1.setAdapter(adapterPatientList);

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
