package com.optimum.AvicaStaff.UI.Doctor.PatientFlow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.optimum.AvicaStaff.Adapters.AdapterMedication;
import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.Medication;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.Utils.AppUtils;

import java.util.ArrayList;

public class MedicationReportActivity extends AppCompatActivity {


    RecyclerView list1;
    ArrayList<Medication> medicationArrayList = new ArrayList<>();
    AdapterMedication adapterMedication;
    String Patient_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button btn = findViewById(R.id.loginBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicationReportActivity.this, CreateMedicationActivity.class);
                intent.putExtra("patient_data", Patient_id); // Ensure PatientList implements Serializable or Parcelable
                startActivity(intent);
                finish();

            }
        });
        list1=findViewById(R.id.list1);

        Patient_id = getIntent().getStringExtra("patient_data");

        getMedication();

    }
    public void getMedication(){
        AppUtils.showProgressDialog(MedicationReportActivity.this);

        AppServices.getMedication(MedicationReportActivity.class.getSimpleName(), Patient_id,new ServiceListener<ArrayList<Medication>, String>() {
            @Override
            public void success(ArrayList<Medication> success) {
                AppUtils.dismisProgressDialog(MedicationReportActivity.this);
                medicationArrayList = success;
                setAdapter();

            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(MedicationReportActivity.this);

            }
        });
    }
    public void setAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list1.setLayoutManager(layoutManager);
        adapterMedication = new AdapterMedication(MedicationReportActivity.this, medicationArrayList, this);
        list1.setAdapter(adapterMedication);

    }


}