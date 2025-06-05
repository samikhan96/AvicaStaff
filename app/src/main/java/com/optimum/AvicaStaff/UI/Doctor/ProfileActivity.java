package com.optimum.AvicaStaff.UI.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.PatientList;
import com.optimum.AvicaStaff.Models.PatientProfile;
import com.optimum.AvicaStaff.Models.User;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.Utils.AppUtils;
import com.optimum.AvicaStaff.Utils.UserPrefs;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView prpfile_img;
    TextView tvName, tvEmail, tvDOB, tvPhone, tvHeight, tvWeight, tvLanguage, tvSSN, tvSubscriberId, tvMeasurementSystem, tvTimeZone;
    PatientProfile patientProfile;
    String Patient_id;
    ImageView item_1,item_2,item_3,item_4,item_5,item_6,item_7,item_8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Patient_id = getIntent().getStringExtra("patient_data");


        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Profile Info
        prpfile_img = findViewById(R.id.prpfile_img);
        tvName = findViewById(R.id.tv_1);
        tvEmail = findViewById(R.id.tv_2);
        tvDOB = findViewById(R.id.tv_3);
        tvPhone = findViewById(R.id.tv_4);
        tvHeight = findViewById(R.id.tv_5);
        tvWeight = findViewById(R.id.tv_6);
        tvLanguage = findViewById(R.id.tv_7);
        tvSSN = findViewById(R.id.tv_8);
        tvSubscriberId = findViewById(R.id.tv_9);
        tvMeasurementSystem = findViewById(R.id.tv_10);
        tvTimeZone = findViewById(R.id.tv_11);
        item_1 = findViewById(R.id.item_1);
        item_2 = findViewById(R.id.item_2);
        item_3 = findViewById(R.id.item_3);
        item_4 = findViewById(R.id.item_4);
        item_5 = findViewById(R.id.item_5);
        item_6 = findViewById(R.id.item_6);
        item_7 = findViewById(R.id.item_7);
        item_8 = findViewById(R.id.item_8);


        item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        item_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        item_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        item_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        item_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MeasurmentsActivity.class));
                finish();

            }
        });
        item_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        item_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        item_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        getProfile(Patient_id);
    }

    public void getProfile(String id){
        AppUtils.showProgressDialog(ProfileActivity.this);

        AppServices.patientProfile(ProfileActivity.class.getSimpleName(),id, new ServiceListener<PatientProfile, String>() {
            @Override
            public void success(PatientProfile success) {
                patientProfile= success;
                AppUtils.dismisProgressDialog(ProfileActivity.this);
                setValues();
            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(ProfileActivity.this);

            }
        });
    }

    public void setValues(){

        // Set Text Values+
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.app_icon)
                .error(R.drawable.app_icon);
        Glide.with(ProfileActivity.this).load(patientProfile.uri).apply(options).into(prpfile_img);

        tvName.setText(patientProfile.first_name + " " + patientProfile.last_name);
        tvEmail.setText(patientProfile.email);
        AppUtils.setFormattedDate(patientProfile.dob,tvDOB);
        tvPhone.setText(patientProfile.phone_number);
        tvHeight.setText(patientProfile.patient.height);
        tvWeight.setText(patientProfile.patient.weight);
        tvLanguage.setText(patientProfile.language);
        tvSSN.setText(patientProfile.SSN);
        tvSubscriberId.setText(patientProfile.subscribeId);
        tvMeasurementSystem.setText(patientProfile.measurementSystem);
        tvTimeZone.setText(patientProfile.timeZone);

    }



}