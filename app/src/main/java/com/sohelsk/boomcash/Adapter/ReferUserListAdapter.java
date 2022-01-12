package com.sohelsk.boomcash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.boomcash.DataModel.ReferUserModel;
import com.sohelsk.boomcash.R;

import java.util.List;

public class ReferUserListAdapter extends RecyclerView.Adapter<ReferUserListAdapter.MyViewHolder>{

    private Context context;
    private List<ReferUserModel> referUserList;

    public ReferUserListAdapter(Context context, List<ReferUserModel> referUserList) {
        this.context = context;
        this.referUserList = referUserList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.refer_user_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      ReferUserModel user=referUserList.get(position);
        String phone=user.getPhone(),name=user.getName();
      if(user.getPhone().length()>5){
          phone="******"+user.getPhone().substring(user.getPhone().length()-5,user.getPhone().length());
      }
      if(user.getName().length()>5){
          name="******"+user.getName().substring(user.getName().length()-5,user.getName().length());
      }
      holder.nameTv.setText("Name: "+name);
      holder.phoneTv.setText(""+phone);
      holder.referCodeTv.setText("Invite Code: "+user.getReferCode());
      holder.myReferCodeTv.setText("User Refer Code: "+user.getMyReferCode());
      holder.packageNameTv.setText("Package Name : "+user.getLevelName());


    }

    @Override
    public int getItemCount() {
        return referUserList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView nameTv,phoneTv,referCodeTv,myReferCodeTv,packageNameTv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTv=itemView.findViewById(R.id.r_nameTvId);
            phoneTv=itemView.findViewById(R.id.r_phoneTvID);
            referCodeTv=itemView.findViewById(R.id.r_referCodeTvId);
            myReferCodeTv=itemView.findViewById(R.id.r_myReferCodeTvId);
            packageNameTv=itemView.findViewById(R.id.r_packageNameTv);
        }
    }

}
