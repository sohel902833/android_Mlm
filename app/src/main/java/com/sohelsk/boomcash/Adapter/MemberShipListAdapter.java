package com.sohelsk.boomcash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.boomcash.DataModel.CurrentMembership;
import com.sohelsk.boomcash.DataModel.MembershipListModel;
import com.sohelsk.boomcash.R;
import com.squareup.picasso.Picasso;

public class MemberShipListAdapter extends RecyclerView.Adapter<MemberShipListAdapter.MyViewHolder>{

    private Context context;
    private  OnItemClickListner listner;
    private MembershipListModel memberShipList;

    public MemberShipListAdapter(Context context, MembershipListModel memberShipList) {
        this.context = context;
        this.memberShipList = memberShipList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.membership_item_layout_2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CurrentMembership currentMembership=memberShipList.getMembership().get(position);
        holder.currentMembershipTv.setText(currentMembership.getLevelName()+" : "+currentMembership.getPrice()+" USD");
        holder.numOfJobsTv.setText("Number Of Jobs Per Day: "+currentMembership.getNumOfJobs());
        holder.monthlyIncomeTv.setText("Monthly Income : "+currentMembership.getMonthlyIncome()+" USD");
        holder.validityPeriodTv.setText("Validity Period Day : "+currentMembership.getValidityDay());
        Picasso.get().load(currentMembership.getImageUrl()).placeholder(R.drawable.sun2).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return memberShipList.getMembership().size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private TextView currentMembershipTv,numOfJobsTv,monthlyIncomeTv,validityPeriodTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.membershipIconImageViewid);

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
