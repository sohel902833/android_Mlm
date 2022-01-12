package com.sohelsk.boomcash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.boomcash.DataModel.PackageModel;
import com.sohelsk.boomcash.R;

import java.util.List;

public class PurchasePackageAdapter extends RecyclerView.Adapter<PurchasePackageAdapter.MyViewHolder>{

    private Context context;
    private List<PackageModel> packageList;
    private  OnItemClickListner listner;

    public PurchasePackageAdapter(Context context, List<PackageModel> packageList) {
        this.context = context;
        this.packageList = packageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.package_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PackageModel pacage=packageList.get(position);
        holder.currentMembershipTv.setText("Package Name: "+pacage.getLevelName());
        holder.numOfJobsTv.setText("Order Number : "+pacage.getOrderNumber());
        holder.monthlyIncomeTv.setText("Status : "+pacage.getState());
        holder.validityPeriodTv.setText("Time : "+pacage.getTime());

    }

    @Override
    public int getItemCount() {
        return packageList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView currentMembershipTv,numOfJobsTv,monthlyIncomeTv,validityPeriodTv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            currentMembershipTv=itemView.findViewById(R.id.i_membershipNameTvId);
            numOfJobsTv=itemView.findViewById(R.id.i_numOfJobsTvId);
            monthlyIncomeTv=itemView.findViewById(R.id.i_monthlyIncomeTvId);
            validityPeriodTv=itemView.findViewById(R.id.i_validityPeriodTvId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listner!=null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listner.onItemClick(position);
                }
            }
        }

    }
    public interface  OnItemClickListner{
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }


}
