package com.optimum.AvicaStaff.UI.Doctor.BloodPressure;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.User;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.Dialogs.Measurement_TakenDialog;
import com.optimum.AvicaStaff.Utils.AppUtils;
import com.optimum.AvicaStaff.Utils.UserPrefs;

import org.json.JSONException;
import org.json.JSONObject;

public class ManualActivity extends AppCompatActivity {

    Button sendBtn;
    EditText et_1,et_2;
    String set_1,set_2;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_manual);

        user= UserPrefs.getGetUser();

        ImageView back = findViewById(R.id.back);
        et_1=findViewById(R.id.et_1);
        et_2=findViewById(R.id.et_2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        sendBtn=findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

                Measurement_TakenDialog cdd = new Measurement_TakenDialog(ManualActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }
        });
    }
    public boolean validate() {
        set_1 = et_1.getText().toString();
        set_2 = et_2.getText().toString();
        if (TextUtils.isEmpty(set_1) ||  TextUtils.isEmpty(set_2) ) {
            AppUtils.Toast("Please fill all fields");
            return false;
        }
        AddBG();
        return true;
    }

    public void AddBG(){
        AppUtils.showProgressDialog(ManualActivity.this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", "Measurement BP");
            jsonObject.put("type", "BLOODPRESSURE");
            jsonObject.put("mode", "METRIC");
            jsonObject.put("unit", "MMHG");
            jsonObject.put("reading_source", "MANUAL");
            jsonObject.put("value1", set_1);
            jsonObject.put("value2", set_1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppServices.AddMeasurment(ManualActivity.class.getSimpleName(),user.id, jsonObject, new ServiceListener<String, String>() {
            @Override
            public void success(String success) {
                AppUtils.dismisProgressDialog(ManualActivity.this);
                Measurement_TakenDialog cdd = new Measurement_TakenDialog(ManualActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(ManualActivity.this);

            }
        });
    }
}