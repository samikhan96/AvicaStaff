package com.optimum.AvicaStaff.UI.Doctor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.HttpUtils.ConfigConstants;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.User;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.Dialogs.LoginDialog;
import com.optimum.AvicaStaff.Utils.AppUtils;
import com.optimum.AvicaStaff.Utils.UserPrefs;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    TextView forgotPassword;
    TextView techSupport;
    TextView privacyPolicy;
    TextView terms;
    EditText et_email, password;
    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.loginBtn);
        forgotPassword = findViewById(R.id.forgotPassword);
        techSupport = findViewById(R.id.techSupport);
        privacyPolicy = findViewById(R.id.privacyPolicy);
        terms = findViewById(R.id.terms);
        et_email = findViewById(R.id.et_email);
        password = findViewById(R.id.password);


//        DOCTORLOGIN
        et_email.setText("muhammadsaqib@avica.com");
        password.setText("Doctor@123");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);

            }
        });
        techSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, TechnicalSupportActivity.class);
                startActivity(intent);

            }
        });
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);

            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, TermsAndConditionActivity.class);
                startActivity(intent);

            }
        });
    }

    public boolean validate() {
        email = et_email.getText().toString();
        pass = password.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            AppUtils.Toast("Please fill all fields");
            return false;
        }
        UserPrefs.getInstance().clearDoctorUser();
        Login();
        return true;
    }

    public void Login() {
        AppUtils.showProgressDialog(LoginActivity.this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppServices.Login(LoginActivity.class.getSimpleName(), jsonObject, new ServiceListener<User, String>() {
            @Override
            public void success(User success) {
                ConfigConstants.token = success.token;
                AppUtils.dismisProgressDialog(LoginActivity.this);
                LoginDialog cdd = new LoginDialog(LoginActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();

            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(LoginActivity.this);

            }
        });
    }
}