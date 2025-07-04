package com.optimum.AvicaStaff.UI.Doctor.PatientFlow;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.optimum.AvicaStaff.R;

import java.util.Calendar;
import java.util.Locale;

public class CreateMedicationActivity extends AppCompatActivity {

    String Patient_id;
    EditText et_name;
    EditText et_1,et_2,et_3,et_4,et_5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);

        et_name=findViewById(R.id.et_name);
        et_1=findViewById(R.id.et_1);
        et_2=findViewById(R.id.et_2);
        et_3=findViewById(R.id.et_3);
        et_4=findViewById(R.id.et_4);
        et_5=findViewById(R.id.et_5);


        Button btn = findViewById(R.id.sendBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Patient_id = getIntent().getStringExtra("patient_data");

        et_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        et_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(et_3);
            }
        });
        et_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(et_3);
            }
        });
        et_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(et_3);
            }
        });


    }


    private void showDatePickerDialog(EditText targetEditText) {
        // Get current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Month is 0-based, so +1
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    targetEditText.setText(selectedDate); // Set selected date to EditText
                }, year, month, day);

        datePickerDialog.show();
    }
    private void showTimePickerDialog(EditText targetEditText) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, selectedHour, selectedMinute) -> {
                    String amPm;
                    if (selectedHour >= 12) {
                        amPm = "PM";
                        if (selectedHour > 12) selectedHour -= 12;
                    } else {
                        amPm = "AM";
                        if (selectedHour == 0) selectedHour = 12;
                    }

                    String formattedTime = String.format(Locale.getDefault(), "%02d:%02d %s", selectedHour, selectedMinute, amPm);
                    targetEditText.setText(formattedTime);
                }, hour, minute, false); // false = 12-hour format

        timePickerDialog.show();
    }

}

