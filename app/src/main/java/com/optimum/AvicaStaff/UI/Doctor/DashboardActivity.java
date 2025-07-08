package com.optimum.AvicaStaff.UI.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;
import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.DashboardData;
import com.optimum.AvicaStaff.Models.Dashboard_BG;
import com.optimum.AvicaStaff.Models.Dashboard_BP;
import com.optimum.AvicaStaff.Models.Dashboard_ECG;
import com.optimum.AvicaStaff.Models.Dashboard_Spo2;
import com.optimum.AvicaStaff.Models.Dashboard_Temp;
import com.optimum.AvicaStaff.Models.User;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.Chat.ChatActivity;
import com.optimum.AvicaStaff.UI.Doctor.PatientFlow.PatientListActivity;
import com.optimum.AvicaStaff.UI.Doctor.TeleMedicine.TelemedActivity;
import com.optimum.AvicaStaff.UI.Doctor.Dialogs.LogoutDialog;
import com.optimum.AvicaStaff.Utils.AppUtils;
import com.optimum.AvicaStaff.Utils.DonutChartView;
import com.optimum.AvicaStaff.Utils.UserPrefs;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity {

    ImageView drawerimage, noti, navFooter1;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CircleImageView drawer_img, profile_img;
    TextView name, drawer_name, specs, drawer_specs,
            bg_totalReading, bg_timeStamp, bg_tv_1, bg_tv_2, bg_tv_3, bg_tv_4,
            bp_totalReading, bp_timeStamp, bp_tv_1, bp_tv_2, bp_tv_3, bp_tv_4,
            Temp_totalReading, Temp_timeStamp, Temp_tv_1, Temp_tv_2, Temp_tv_3, Temp_tv_4,
            spo2_totalReading, spo2_timeStamp, spo2_tv_1, spo2_tv_2, spo2_tv_3, spo2_tv_4,
            ecg_totalReading, ecg_timeStamp, ecg_tv_1, ecg_tv_2, ecg_tv_3, ecg_tv_4;
    LinearLayout l2, l3;
    User user;
    DonutChartView bg_donutChart, bp_donutChart, Temp_donutChart, spo2_donutChart, ecg_donutChart;
    Dashboard_BG dashboardBg;
    Dashboard_BP dashboardBp;
    Dashboard_Temp dashboardTemp;
    Dashboard_Spo2 dashboardspo2;
    Dashboard_ECG dashboardECG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        user = UserPrefs.getGetUser();

        drawerimage = findViewById(R.id.drawerimage);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navview);
        View header = navigationView.getHeaderView(0);
        drawer_img = header.findViewById(R.id.drawer_img);
        drawer_name = header.findViewById(R.id.drawer_name);
        drawer_specs = header.findViewById(R.id.drawer_specs);
        profile_img = findViewById(R.id.profile_img);
        name = findViewById(R.id.name);
        specs = findViewById(R.id.specs);
        navFooter1 = findViewById(R.id.footer_item_1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        noti = findViewById(R.id.noti);
        bg_donutChart = findViewById(R.id.bg_donutChart);
        bg_totalReading = findViewById(R.id.bg_totalReading);
        bg_timeStamp = findViewById(R.id.bg_timeStamp);
        bg_tv_1 = findViewById(R.id.bg_tv_1);
        bg_tv_2 = findViewById(R.id.bg_tv_2);
        bg_tv_3 = findViewById(R.id.bg_tv_3);
        bg_tv_4 = findViewById(R.id.bg_tv_4);

        bp_donutChart = findViewById(R.id.bp_donutChart);
        bp_totalReading = findViewById(R.id.bp_totalReading);
        bp_timeStamp = findViewById(R.id.bp_timeStamp);
        bp_tv_1 = findViewById(R.id.bp_tv_1);
        bp_tv_2 = findViewById(R.id.bp_tv_2);
        bp_tv_3 = findViewById(R.id.bp_tv_3);
        bp_tv_4 = findViewById(R.id.bp_tv_4);

        Temp_donutChart = findViewById(R.id.Temp_donutChart);
        Temp_totalReading = findViewById(R.id.Temp_totalReading);
        Temp_timeStamp = findViewById(R.id.Temp_timeStamp);
        Temp_tv_1 = findViewById(R.id.Temp_tv_1);
        Temp_tv_2 = findViewById(R.id.Temp_tv_2);
        Temp_tv_3 = findViewById(R.id.Temp_tv_3);
        Temp_tv_4 = findViewById(R.id.Temp_tv_4);

        spo2_donutChart = findViewById(R.id.spo2_donutChart);
        spo2_totalReading = findViewById(R.id.spo2_totalReading);
        spo2_timeStamp = findViewById(R.id.spo2_timeStamp);
        spo2_tv_1 = findViewById(R.id.spo2_tv_1);
        spo2_tv_2 = findViewById(R.id.spo2_tv_2);
        spo2_tv_3 = findViewById(R.id.spo2_tv_3);
        spo2_tv_4 = findViewById(R.id.spo2_tv_4);

        ecg_donutChart = findViewById(R.id.ecg_donutChart);
        ecg_totalReading = findViewById(R.id.ecg_totalReading);
        ecg_timeStamp = findViewById(R.id.ecg_timeStamp);
        ecg_tv_1 = findViewById(R.id.ecg_tv_1);
        ecg_tv_2 = findViewById(R.id.ecg_tv_2);
        ecg_tv_3 = findViewById(R.id.ecg_tv_3);
        ecg_tv_4 = findViewById(R.id.ecg_tv_4);

        name.setText(user.first_name + " " + user.last_name);
        drawer_name.setText(user.first_name + " " + user.last_name);
        specs.setText(user.speciality);
        drawer_specs.setText(user.speciality);
        // Set Text Values+
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.app_icon)
                .error(R.drawable.app_icon);
        Glide.with(DashboardActivity.this).load(user.uri).apply(options).into(drawer_img);
        Glide.with(DashboardActivity.this).load(user.uri).apply(options).into(profile_img);

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, RAGActivity.class));
                finish();
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, PatientListActivity.class));
                finish();

            }
        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, NotificationActivity.class));
            }
        });

        navFooter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
                LogoutDialog cdd = new LogoutDialog(DashboardActivity.this);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
            }
        });
        drawerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        drawer_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, DocProfileActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);


            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.item1:
                        //close drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.item2:
                        //close drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(DashboardActivity.this, DocProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        //close drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(DashboardActivity.this, ChatActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item4:
                        //close drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(DashboardActivity.this, TelemedActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.item5:
                        //close drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(DashboardActivity.this, EducationActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.item6:
                        //close drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(DashboardActivity.this, ComplianceActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.item7:
                        //close drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        intent = new Intent(DashboardActivity.this, RAGActivity.class);
                        startActivity(intent);
                        break;


                }

                return true;
            }
        });
        getDocDashboard();
    }


    private void setupBgPieChart(int high, int normal, int low,int pending) {
        int total = high + normal + low + pending;

        bg_totalReading.setText("" + total);
        // Example values
        int[] values = {high, normal, low, pending};
        int[] colors = {
                0xFFEF5DA8,
                0xFF2CC97D,
                0xFFF7B500,
                0xFF0B75F9
        };
        bg_donutChart.setValues(values);
        bg_donutChart.setColors(colors);

    }

    private void setupBpPieChart(int high, int normal, int low,int pending) {
        int total = high + normal + low + pending;

        bp_totalReading.setText("" + total);
        // Example values
        int[] values = {high, normal, low,pending};
        int[] colors = {
                0xFFEF5DA8,
                0xFF2CC97D,
                0xFFF7B500,
                0xFF0B75F9

        };
        bp_donutChart.setValues(values);
        bp_donutChart.setColors(colors);

    }

    private void setupTempPieChart(int high, int normal, int low,int pending) {
        int total = high + normal + low + pending;

        Temp_totalReading.setText("" + total);
        // Example values
        int[] values = {high, normal, low,pending};
        int[] colors = {
                0xFFEF5DA8,
                0xFF2CC97D,
                0xFFF7B500,
                0xFF0B75F9

        };
        Temp_donutChart.setValues(values);
        Temp_donutChart.setColors(colors);

    }

    private void setupspo2PieChart(int high, int normal, int low,int pending) {
        int total = high + normal + low + pending;

        spo2_totalReading.setText("" + total);
        // Example values
        int[] values = {high, normal, low,pending};
        int[] colors = {
                0xFFEF5DA8,
                0xFF2CC97D,
                0xFFF7B500,
                0xFF0B75F9

        };
        spo2_donutChart.setValues(values);
        spo2_donutChart.setColors(colors);

    }

    private void setupecgPieChart(int high, int normal, int low,int pending) {
        int total = high + normal + low + pending;

        ecg_totalReading.setText("" + total);
        // Example values
        int[] values = {high, normal, low,pending};
        int[] colors = {
                0xFFEF5DA8,
                0xFF2CC97D,
                0xFFF7B500,
                0xFF0B75F9

        };
        ecg_donutChart.setValues(values);
        ecg_donutChart.setColors(colors);

    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }


    public void getDocDashboard() {
        AppUtils.showProgressDialog(DashboardActivity.this);

        AppServices.Dashboard_doc(DashboardActivity.class.getSimpleName(), new ServiceListener<DashboardData, String>() {
            @Override
            public void success(DashboardData success) {
                AppUtils.dismisProgressDialog(DashboardActivity.this);
                dashboardBg = success.getBloodglucose();
                dashboardBp = success.getBloodpressure();
                dashboardTemp = success.getTemperature();
                dashboardspo2 = success.getSpo2();
                dashboardECG = success.getEcg();
                setBGdata();
                setBPdata();
                setTempdata();
                setspo2data();
                setecgdata();
            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(DashboardActivity.this);

            }

        });
    }

    public void setBGdata() {
        bg_tv_1.setText("• High:   " + "(" + dashboardBg.high + ")");
        bg_tv_2.setText("• Normal:   " + "(" + dashboardBg.normal + ")");
        bg_tv_3.setText("• Low:   " + "(" + dashboardBg.low + ")");
        bg_tv_4.setText("• Alert Not set:   " + "(" + dashboardBg.alert_not_set + ")");
        bg_timeStamp.setText("6 pm 12-06-20");
        setupBgPieChart(dashboardBg.high, dashboardBg.normal, dashboardBg.low,dashboardBg.alert_not_set);
    }

    public void setBPdata() {
        bp_tv_1.setText("• High:   " + "(" + dashboardBp.high + ")");
        bp_tv_2.setText("• Normal:   " + "(" + dashboardBp.normal + ")");
        bp_tv_3.setText("• Low:   " + "(" + dashboardBp.low + ")");
        bp_tv_4.setText("• Alert Not set:   " + "(" + dashboardBp.alert_not_set + ")");
        bp_timeStamp.setText("6 pm 12-06-20");
        setupBpPieChart(dashboardBp.high, dashboardBp.normal, dashboardBp.low,dashboardBp.alert_not_set);
    }

    public void setTempdata() {
        Temp_tv_1.setText("• High:   " + "(" + dashboardTemp.high + ")");
        Temp_tv_2.setText("• Normal:   " + "(" + dashboardTemp.normal + ")");
        Temp_tv_3.setText("• Low:   " + "(" + dashboardTemp.low + ")");
        Temp_tv_4.setText("• Alert Not set:   " + "(" + dashboardTemp.alert_not_set + ")");
        Temp_timeStamp.setText("6 pm 12-06-20");
        setupTempPieChart(dashboardTemp.high, dashboardTemp.normal, dashboardTemp.low,dashboardTemp.alert_not_set);
    }

    public void setspo2data() {
        spo2_tv_1.setText("• High:   " + "(" + dashboardspo2.high + ")");
        spo2_tv_2.setText("• Normal:   " + "(" + dashboardspo2.normal + ")");
        spo2_tv_3.setText("• Low:   " + "(" + dashboardspo2.low + ")");
        spo2_tv_4.setText("• Alert Not set:   " + "(" + dashboardspo2.alert_not_set + ")");
        spo2_timeStamp.setText("6 pm 12-06-20");
        setupspo2PieChart(dashboardspo2.high, dashboardspo2.normal, dashboardspo2.low,dashboardspo2.alert_not_set);
    }

    public void setecgdata() {
        ecg_tv_1.setText("• High:   " + "(" + dashboardECG.high + ")");
        ecg_tv_2.setText("• Normal:   " + "(" + dashboardECG.normal + ")");
        ecg_tv_3.setText("• Low:   " + "(" + dashboardECG.low + ")");
        ecg_tv_4.setText("• Alert Not set:   " + "(" + dashboardECG.alert_not_set + ")");
        ecg_timeStamp.setText("6 pm 12-06-20");
        setupecgPieChart(dashboardECG.high, dashboardECG.normal, dashboardECG.low,dashboardECG.alert_not_set);
    }

}