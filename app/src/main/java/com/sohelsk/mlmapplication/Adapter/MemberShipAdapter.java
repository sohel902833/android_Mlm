package com.sohelsk.mlmapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.mlmapplication.R;

public class MemberShipAdapter extends RecyclerView.Adapter<MemberShipAdapter.MyViewHolder>{

    private Context context;
    private String[] memberShipList;
    private  OnItemClickListner listner;

    public MemberShipAdapter(Context context, String[] memberShipList) {
        this.context = context;
        this.memberShipList = memberShipList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.membership_list_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.textView.setText(""+memberShipList[position]);

    }

    @Override
    public int getItemCount() {
        return memberShipList.length;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.membershipTv);

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
