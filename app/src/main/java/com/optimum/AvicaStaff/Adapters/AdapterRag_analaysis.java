package com.optimum.AvicaStaff.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.optimum.AvicaStaff.Models.Notifications;
import com.optimum.AvicaStaff.Models.RAG;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.Utils.AppUtils;

import java.util.ArrayList;

public class AdapterRag_analaysis extends RecyclerView.Adapter<AdapterRag_analaysis.MyViewHolder> {

    ArrayList<RAG> pamModelArrayList;
    private Context context;
    public LayoutInflater layoutInflater;


    public AdapterRag_analaysis(Activity activity, ArrayList<RAG> pamModels, Context context) {
        this.pamModelArrayList = pamModels;
        this.context = context;
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public AdapterRag_analaysis.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdapterRag_analaysis.MyViewHolder(layoutInflater.inflate(R.layout.rag_analaysis_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AdapterRag_analaysis.MyViewHolder holder, int position) {
        RAG pamModel = pamModelArrayList.get(position);

        holder.tv_1.setText(pamModel.patient);
        holder.tv_2.setText(pamModel.value1);
        holder.tv_3.setText(pamModel.unit);
        holder.tv_4.setText(pamModel.type);
        holder.tv_5.setText(pamModel.status);
        holder.tv_6.setText(pamModel.type);
//        AppUtils.setFormattedDate(pamModel.created_at,holder.tv_5);




    }

    @Override
    public int getItemCount() {
        return pamModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_1);
            tv_2 =  itemView.findViewById(R.id.tv_2);
            tv_3 =  itemView.findViewById(R.id.tv_3);
            tv_4 =  itemView.findViewById(R.id.tv_4);
            tv_5 =  itemView.findViewById(R.id.tv_5);
            tv_6 =  itemView.findViewById(R.id.tv_6);

        }


    }
}






