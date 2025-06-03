package com.optimum.AvicaStaff.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.optimum.AvicaStaff.Models.Reports;
import com.optimum.AvicaStaff.R;

import java.util.ArrayList;

public class AdapterReport extends RecyclerView.Adapter<AdapterReport.MyViewHolder> {

    ArrayList<Reports> pamModelArrayList;
    private Context context;
    public LayoutInflater layoutInflater;


    public AdapterReport(Activity activity, ArrayList<Reports> pamModels, Context context) {
        this.pamModelArrayList = pamModels;
        this.context = context;
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public AdapterReport.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdapterReport.MyViewHolder(layoutInflater.inflate(R.layout.medication_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AdapterReport.MyViewHolder holder, int position) {
        Reports pamModel = pamModelArrayList.get(position);

        holder.tv_1.setText(pamModel.title);
        holder.tv_2.setText(pamModel.title);
        holder.tv_3.setText(pamModel.title);
        holder.tv_4.setText(pamModel.title);
        holder.tv_5.setText(pamModel.title);



    }

    @Override
    public int getItemCount() {
        return pamModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_1,tv_2,tv_3,tv_4,tv_5;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_1);
            tv_2 =  itemView.findViewById(R.id.tv_2);
            tv_3 =  itemView.findViewById(R.id.tv_3);
            tv_4 =  itemView.findViewById(R.id.tv_4);
            tv_5 =  itemView.findViewById(R.id.tv_5);

        }


    }
}







