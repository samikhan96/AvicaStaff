package com.optimum.AvicaStaff.UI.Doctor.PatientFlow.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.optimum.AvicaStaff.R;

public class AddNurseDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes_btn;

    public AddNurseDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_nurse_dialog);
        yes_btn = (Button) findViewById(R.id.yes_btn);


        yes_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes_btn:
                dismiss();
                c.finish();
                break;

            default:
                break;
        }
        dismiss();
    }
}




