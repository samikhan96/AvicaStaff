package com.optimum.AvicaStaff.UI.Doctor.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.DashboardActivity;

public class Appointment_Alert_Doalog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes_btn;

    public Appointment_Alert_Doalog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.appointment_alert_doalog);
        yes_btn = (Button) findViewById(R.id.yes_btn);


        yes_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes_btn:
                c.startActivity(new Intent(c, DashboardActivity.class));
                c.finish();
                break;

            default:
                break;
        }
        dismiss();
    }
}



