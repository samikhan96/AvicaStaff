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
        return new AdapterRag_analaysis.MyViewHolder(layoutInflater.inflate(R.layout.pushnoti_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AdapterRag_analaysis.MyViewHolder holder, int position) {
        RAG pamModel = pamModelArrayList.get(position);

        holder.text_heading.setText(pamModel.title);
        holder.text_discription.setText(pamModel.type);



    }

    @Override
    public int getItemCount() {
        return pamModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text_heading,text_discription;

        public MyViewHolder(View itemView) {
            super(itemView);
            text_heading = itemView.findViewById(R.id.text_heading);
            text_discription =  itemView.findViewById(R.id.text_discription);

        }


    }
}






