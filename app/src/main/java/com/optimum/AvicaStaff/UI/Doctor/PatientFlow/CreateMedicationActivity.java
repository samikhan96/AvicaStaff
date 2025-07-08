package com.optimum.AvicaStaff.UI.Doctor.PatientFlow;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.Medicine;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.Dialogs.AddMedicationDialog;
import com.optimum.AvicaStaff.Utils.AppUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CreateMedicationActivity extends AppCompatActivity {

    String Patient_id;
    EditText et_name;
    EditText et_1, et_2, et_3, et_4, et_5;
    ArrayList<Medicine> medicineArrayList = new ArrayList<>();

    private Medicine selectedDoctor = null;
    private String selectedDoctorId = ""; // Declare at class level


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);

        et_name = findViewById(R.id.et_name);
        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        et_3 = findViewById(R.id.et_3);
        et_4 = findViewById(R.id.et_4);
        et_5 = findViewById(R.id.et_5);


        Button btn = findViewById(R.id.sendBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_1.getText().toString().equalsIgnoreCase("")
                        ||et_name.getText().toString().equalsIgnoreCase("")
                        ||et_2.getText().toString().equalsIgnoreCase("")
                        ||et_3.getText().toString().equalsIgnoreCase("")
                        ||et_4.getText().toString().equalsIgnoreCase("")
                        ||et_5.getText().toString().equalsIgnoreCase("") ){
                    AppUtils.Toast("Please fill all fields");
                }
                else {
                    CreateMedication();
                }

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

        et_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMedicine();
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

    public void getMedicine() {
        AppUtils.showProgressDialog(CreateMedicationActivity.this);

        AppServices.getMedicine(CreateMedicationActivity.class.getSimpleName(), new ServiceListener<ArrayList<Medicine>, String>() {
            @Override
            public void success(ArrayList<Medicine> success) {
                AppUtils.dismisProgressDialog(CreateMedicationActivity.this);
                medicineArrayList=success;
                try {
                    runOnUiThread(() -> showDoctorDialog());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(CreateMedicationActivity.this);

            }
        });
    }

    private void showDoctorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_search_medicine, null);
        builder.setView(dialogView);

        SearchView searchView = dialogView.findViewById(R.id.searchView);
        ListView listView = dialogView.findViewById(R.id.listView);

        // Create adapter with doctor names
        List<String> doctorNamesList = new ArrayList<>();
        for (int i = 0; i < medicineArrayList.size(); i++) {
            doctorNamesList.add(medicineArrayList.get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, doctorNamesList);
        listView.setAdapter(adapter);

        // Enable search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Handled by onQueryTextChange
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Handle list item click
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = adapter.getItem(position);
            for (int i = 0; i < medicineArrayList.size(); i++) {
                if (medicineArrayList.get(i).getName().equals(selectedName)) {
                    selectedDoctor = medicineArrayList.get(i);
                    break;
                }
            }
            et_name.setText(selectedDoctor.getName());
            selectedDoctorId = selectedDoctor.getId();
            dialog.dismiss();
        });
    }

    public void CreateMedication(){

        AppUtils.showProgressDialog(CreateMedicationActivity.this);

        JSONObject json = new JSONObject();
        try {
            json.put("name", et_name.getText().toString()); // Medicine name
            json.put("start_date", et_1.getText().toString()); // or format from DatePicker
            json.put("end_date", et_2.getText().toString());
            json.put("time", et_3.getText().toString());
            json.put("dosage", et_4.getText().toString());     // e.g., "10mg"
            json.put("frequency", et_5.getText().toString());  // e.g., "1" (per day)
            json.put("unit", "TABLET");                    // e.g., "TABLET", "CAPSULE", etc.

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppServices.CreateMedication(CreateMedicationActivity.class.getSimpleName(),Patient_id, json, new ServiceListener<String, String>() {
            @Override
            public void success(String success) {
                AppUtils.dismisProgressDialog(CreateMedicationActivity.this);
                AddMedicationDialog cdd = new AddMedicationDialog(CreateMedicationActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();

            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(CreateMedicationActivity.this);

            }
        });
    }


}

