package com.sohelsk.boomcash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.boomcash.DataModel.TaskListModel;
import com.sohelsk.boomcash.DataModel.TaskModel;
import com.sohelsk.boomcash.R;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.MyViewHolder>{

    private Context context;
    private  OnItemClickListner listner;
    private TaskListModel taskList;

    public TaskListAdapter(Context context, TaskListModel taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.task_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TaskModel task=taskList.getMyTasks().get(position);

        holder.taskTypeTvId2.setText("Like");
        holder.taskPriceTvId.setText(""+task.getPrice());
        holder.taskSerialTvId.setText(""+task.getId());
        holder.taskIdTvId.setText(""+System.currentTimeMillis());
        if(task.getBrand().equals("Tiktak")){
            holder.imageView.setImageResource(R.drawable.youtube);
            holder.taskTypeTvId.setText("Youtube");
        }else{
            holder.taskTypeTvId.setText(""+task.getBrand());
        }
        holder.receiverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onReceiveClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.getMyTasks().size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private TextView taskTypeTvId,taskTypeTvId2,taskPriceTvId,taskSerialTvId,taskIdTvId;
        private Button receiverButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.icon_imageViewId);
            taskTypeTvId=itemView.findViewById(R.id.task_typeTV);
            taskTypeTvId2=itemView.findViewById(R.id.task_typeTV2);
            taskIdTvId=itemView.findViewById(R.id.taskId_TvId);
            taskPriceTvId=itemView.findViewById(R.id.task_amountTv);
            taskSerialTvId=itemView.findViewById(R.id.serial_idTV);
            receiverButton=itemView.findViewById(R.id.receiveId);

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
        void onReceiveClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }


}
