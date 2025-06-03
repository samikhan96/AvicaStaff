package com.optimum.AvicaStaff.UI.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.optimum.AvicaStaff.Adapters.AdapterReport;
import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.Reports;
import com.optimum.AvicaStaff.Models.User;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.Utils.AppUtils;
import com.optimum.AvicaStaff.Utils.UserPrefs;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    RecyclerView list1;
    ArrayList<Reports> reportsArrayList = new ArrayList<>();
    AdapterReport adapterReport;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
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
        AppUtils.showProgressDialog(ReportActivity.this);

        AppServices.getReports(ReportActivity.class.getSimpleName(),user.id, new ServiceListener<ArrayList<Reports>, String>() {
            @Override
            public void success(ArrayList<Reports> success) {
                AppUtils.dismisProgressDialog(ReportActivity.this);
                reportsArrayList = success;
                setAdapter();

            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(ReportActivity.this);

            }
        });
    }
    public void setAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list1.setLayoutManager(layoutManager);
        adapterReport = new AdapterReport(ReportActivity.this, reportsArrayList, this);
        list1.setAdapter(adapterReport);

    }


}