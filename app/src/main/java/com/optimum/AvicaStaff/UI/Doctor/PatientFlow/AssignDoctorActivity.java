package com.optimum.AvicaStaff.UI.Doctor.PatientFlow;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.PatientFlow.Dialogs.ADdDoctorDialog;
import com.optimum.AvicaStaff.UI.Doctor.PatientFlow.Dialogs.AddContactDialog;

public class AssignDoctorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_doctor);

        Button loginBtn = findViewById(R.id.sendBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ADdDoctorDialog cdd = new ADdDoctorDialog(AssignDoctorActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }
        });
    }
}
