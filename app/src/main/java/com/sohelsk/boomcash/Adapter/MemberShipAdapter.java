package com.sohelsk.boomcash.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.boomcash.DataModel.CurrentMembership;
import com.sohelsk.boomcash.DataModel.MembershipListModel;
import com.sohelsk.boomcash.LocalDb.UserDb;
import com.sohelsk.boomcash.R;

public class MemberShipAdapter extends RecyclerView.Adapter<MemberShipAdapter.MyViewHolder>{
    private UserDb userDb;
    private Activity context;
    private MembershipListModel memberShipList;
    private  OnItemClickListner listner;

    public MemberShipAdapter(Activity context, MembershipListModel memberShipList) {
        userDb=new UserDb(context);
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
        CurrentMembership membership=memberShipList.getMembership().get(position);
       holder.textView.setText(""+membership.getLevelName());
       if(userDb.getUserData().getCurrentMembership().getLevelName()!=null){
           if(membership.getLevelName().equals(userDb.getUserData().getCurrentMembership().getLevelName())){
               holder.itemView.setBackgroundResource(R.color.tomato);
               holder.textView.setTextColor(context.getResources().getColor(R.color.white));
           }
       }

    }

    @Override
    public int getItemCount() {
        return memberShipList.getMembership().size();
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
