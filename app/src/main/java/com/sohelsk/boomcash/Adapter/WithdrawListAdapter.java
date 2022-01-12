package com.sohelsk.boomcash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.boomcash.DataModel.WithdrawModel;
import com.sohelsk.boomcash.R;

import java.util.List;

public class WithdrawListAdapter extends RecyclerView.Adapter<WithdrawListAdapter.MyViewHolder>{

    private Context context;
    private List<WithdrawModel> withdrawlList;
    private  OnItemClickListner listner;

    public WithdrawListAdapter(Context context, List<WithdrawModel> withdrawlList) {
        this.context = context;
        this.withdrawlList = withdrawlList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.earning_history_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WithdrawModel withdraw=withdrawlList.get(position);
        holder.earnFormTv.setText("Status: "+withdraw.getState());
        holder.dateTv.setText("Date : "+withdraw.getTime());
        holder.amountTv.setText("Amount : "+withdraw.getAmount());


    }

    @Override
    public int getItemCount() {
        return withdrawlList.size();
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
