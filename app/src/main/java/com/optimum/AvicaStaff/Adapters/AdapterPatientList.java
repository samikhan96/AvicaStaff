package com.optimum.AvicaStaff.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.optimum.AvicaStaff.Models.PatientList;
import com.optimum.AvicaStaff.R;
import com.optimum.AvicaStaff.UI.Doctor.PatientFlow.ProfileActivity;

import java.util.ArrayList;

public class AdapterPatientList extends RecyclerView.Adapter<AdapterPatientList.MyViewHolder> {

    ArrayList<PatientList> pamModelArrayList;
    private Context context;
    public LayoutInflater layoutInflater;


    public AdapterPatientList(Activity activity, ArrayList<PatientList> pamModels, Context context) {
        this.pamModelArrayList = pamModels;
        this.context = context;
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public AdapterPatientList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdapterPatientList.MyViewHolder(layoutInflater.inflate(R.layout.patient_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AdapterPatientList.MyViewHolder holder, int position) {
        PatientList pamModel = pamModelArrayList.get(position);

        holder.tv_1.setText(pamModel.user.first_name + " " + pamModel.user.last_name);
        holder.tv_2.setText(pamModel.user.email);
        holder.tv_3.setText(pamModel.emr);
        holder.tv_4.setText(pamModel.user.phone_number);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.app_icon)
                .error(R.drawable.app_icon);
        Glide.with(context).load(pamModel.user.getUri()).apply(options).into(holder.img);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("patient_data", pamModel.user.id); // Ensure PatientList implements Serializable or Parcelable
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return pamModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_1,tv_2,tv_3,tv_4,tv_5;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_1);
            tv_2 =  itemView.findViewById(R.id.tv_2);
            tv_3 =  itemView.findViewById(R.id.tv_3);
            tv_4 =  itemView.findViewById(R.id.tv_4);
            tv_5 =  itemView.findViewById(R.id.tv_5);
            img =  itemView.findViewById(R.id.img);

        }


    }
}







