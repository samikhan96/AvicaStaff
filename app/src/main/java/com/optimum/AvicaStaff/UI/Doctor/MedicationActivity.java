package com.optimum.AvicaStaff.UI.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.optimum.AvicaStaff.Adapters.AdapterMedication;
import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.Medication;
import com.optimum.AvicaStaff.Models.User;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.Utils.AppUtils;
import com.optimum.AvicaStaff.Utils.UserPrefs;

import java.util.ArrayList;

public class MedicationActivity extends AppCompatActivity {


    RecyclerView list1;
    ArrayList<Medication> medicationArrayList = new ArrayList<>();
    AdapterMedication adapterMedication;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        user = UserPrefs.getGetUser();
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list1=findViewById(R.id.list1);
        getMedication();

    }
    public void getMedication(){
        AppUtils.showProgressDialog(MedicationActivity.this);

        AppServices.getMedication(MedicationActivity.class.getSimpleName(), user.id,new ServiceListener<ArrayList<Medication>, String>() {
            @Override
            public void success(ArrayList<Medication> success) {
                AppUtils.dismisProgressDialog(MedicationActivity.this);
                medicationArrayList = success;
                setAdapter();

            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(MedicationActivity.this);

            }
        });
    }
    public void setAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list1.setLayoutManager(layoutManager);
        adapterMedication = new AdapterMedication(MedicationActivity.this, medicationArrayList, this);
        list1.setAdapter(adapterMedication);

    }


}