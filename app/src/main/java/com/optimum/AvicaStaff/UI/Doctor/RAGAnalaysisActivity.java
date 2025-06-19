package com.optimum.AvicaStaff.UI.Doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.optimum.AvicaStaff.Adapters.AdapterPush;
import com.optimum.AvicaStaff.Adapters.AdapterRag_analaysis;
import com.optimum.AvicaStaff.HttpUtils.AppServices;
import com.optimum.AvicaStaff.Listener.ServiceListener;
import com.optimum.AvicaStaff.Models.Notifications;
import com.optimum.AvicaStaff.Models.RAG;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.Utils.AppUtils;

import java.util.ArrayList;

public class RAGAnalaysisActivity extends AppCompatActivity {


    ArrayList<RAG> ragArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterRag_analaysis adapterRagAnalaysis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rag_analaysis);
        recyclerView = findViewById(R.id.list1);


        ImageView back = findViewById(R.id.back);
        TextView type = findViewById(R.id.type);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (RAGActivity.RAG_type.equalsIgnoreCase("ECG")){
            type.setText("ECG");
        }
        if (RAGActivity.RAG_type.equalsIgnoreCase("spo2")){
            type.setText("Spo2");
        }
        if (RAGActivity.RAG_type.equalsIgnoreCase("BLOODPRESSURE")){
            type.setText("Blood Presure");
        }
        if (RAGActivity.RAG_type.equalsIgnoreCase("BLOODGLUCOSE")){
            type.setText("Blood Glucose");
        }
        if (RAGActivity.RAG_type.equalsIgnoreCase("TEMPERATURE")){
            type.setText("Temperature");
        }

        getRAG();
    }
    public void getRAG() {
        AppUtils.showProgressDialog(RAGAnalaysisActivity.this);

        AppServices.getRAG(RAGAnalaysisActivity.class.getSimpleName(),RAGActivity.RAG_type, new ServiceListener<ArrayList<RAG>, String>() {
            @Override
            public void success(ArrayList<RAG> success) {

                AppUtils.dismisProgressDialog(RAGAnalaysisActivity.this);
                ragArrayList = success;
                setAdapter();
            }

            @Override
            public void error(String error) {
                AppUtils.dismisProgressDialog(RAGAnalaysisActivity.this);

            }
        });
    }

    public void setAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapterRagAnalaysis = new AdapterRag_analaysis(RAGAnalaysisActivity.this, ragArrayList, this);
        recyclerView.setAdapter(adapterRagAnalaysis);

    }

}
