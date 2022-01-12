package com.sohelsk.boomcash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.boomcash.DataModel.EarningHistoryModel;
import com.sohelsk.boomcash.R;

import java.util.List;

public class EarningHistoryAdapter extends RecyclerView.Adapter<EarningHistoryAdapter.MyViewHolder>{

    private Context context;
    private List<EarningHistoryModel> earningHistoryList;
    private  OnItemClickListner listner;

    public EarningHistoryAdapter(Context context, List<EarningHistoryModel> earningHistoryList) {
        this.context = context;
        this.earningHistoryList = earningHistoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.earning_history_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EarningHistoryModel earningHistory=earningHistoryList.get(position);
        holder.earnFormTv.setText("Total Earnings");
        holder.dateTv.setText("Date : "+earningHistory.getDate());
        holder.amountTv.setText("Amount : "+earningHistory.getAmount());

    }

    @Override
    public int getItemCount() {
        return earningHistoryList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView earnFormTv,dateTv,amountTv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            earnFormTv=itemView.findViewById(R.id.earnFormTextViewid);
            dateTv=itemView.findViewById(R.id.timeTvId);
            amountTv=itemView.findViewById(R.id.amountTvId);
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
