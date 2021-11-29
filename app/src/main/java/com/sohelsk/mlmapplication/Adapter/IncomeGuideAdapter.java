package com.sohelsk.mlmapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.mlmapplication.DataModel.IncomeGuideModel;
import com.sohelsk.mlmapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IncomeGuideAdapter extends RecyclerView.Adapter<IncomeGuideAdapter.MyViewHolder>{

    private Context context;
    private List<IncomeGuideModel> incomeGuideList;
    private  OnItemClickListner listner;

    public IncomeGuideAdapter(Context context, List<IncomeGuideModel> incomeGuideList) {
        this.context = context;
        this.incomeGuideList = incomeGuideList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.income_guide_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(incomeGuideList.get(position).getImageUrl()).placeholder(R.drawable.sun2).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return incomeGuideList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageViewid);
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
