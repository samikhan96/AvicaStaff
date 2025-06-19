package com.optimum.AvicaStaff.UI.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.Dialogs.EmailSendDialog;
import com.optimum.AvicaStaff.Utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button loginBtn;
    EditText et_email;
    String email;
    public static String emailsaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);



        loginBtn=findViewById(R.id.loginBtn);
        et_email=findViewById(R.id.et_email);
        et_email.setText("muhammadsaqib@avica.com");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();


            }
        });
    }

    public boolean validate() {
        email = et_email.getText().toString();
        if (TextUtils.isEmpty(email) ) {
            AppUtils.Toast("Please fill all fields");
            return false;
        }
        ForgetPassword();
        return true;
    }

    public void ForgetPassword(){
        AppUtils.showProgressDialog(ForgetPasswordActivity.this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("value", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppServices.ForgetPassword(ForgetPasswordActivity.class.getSimpleName(), jsonObject, new ServiceListener<String, String>() {
            @Override
            public void success(String success) {
                emailsaved=email;
                AppUtils.dismisProgressDialog(ForgetPasswordActivity.this);
                EmailSendDialog cdd = new EmailSendDialog(ForgetPasswordActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();


            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(ForgetPasswordActivity.this);

            }
        });
    }

}