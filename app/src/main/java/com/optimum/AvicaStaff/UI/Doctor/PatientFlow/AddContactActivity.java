package com.optimum.AvicaStaff.UI.Doctor.PatientFlow;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.Dialogs.RequestDialog;
import com.optimum.AvicaStaff.UI.Doctor.PatientFlow.Dialogs.AddContactDialog;
import com.optimum.AvicaStaff.UI.Doctor.TechnicalSupportActivity;

public class AddContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Button loginBtn = findViewById(R.id.sendBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddContactDialog cdd = new AddContactDialog(AddContactActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }
        });




    }
}
