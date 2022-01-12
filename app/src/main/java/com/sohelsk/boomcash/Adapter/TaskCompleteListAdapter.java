package com.sohelsk.boomcash.Adapter;

import android.app.Activity;
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
import com.sohelsk.boomcash.LocalDb.JobViewDb;
import com.sohelsk.boomcash.R;

public class TaskCompleteListAdapter extends RecyclerView.Adapter<TaskCompleteListAdapter.MyViewHolder>{

    private Activity context;
    private  OnItemClickListner listner;
    private TaskListModel taskList;
    private JobViewDb jobViewDb;
    public TaskCompleteListAdapter(Activity context, TaskListModel taskList) {
        this.context = context;
        this.taskList = taskList;
        jobViewDb=new JobViewDb(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.task_submit_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TaskModel task=taskList.getMyTasks().get(position);
        holder.taskTypeTvId.setText("Task Income :"+task.getPrice());
        if(task.getBrand().equals("Tiktak")){
            holder.imageView.setImageResource(R.drawable.youtube);
        }

        holder.forwardButton.setVisibility(View.GONE);
        holder.submitButton.setVisibility(View.GONE);
        holder.videoViewButton.setVisibility(View.GONE);
        holder.taskSampleBtn.setVisibility(View.GONE);



    }

    @Override
    public int getItemCount() {
        return taskList.getMyTasks().size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private TextView taskTypeTvId,descTv;
        private Button forwardButton,submitButton,videoViewButton,taskSampleBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.icon_imageViewId);
            taskTypeTvId=itemView.findViewById(R.id.task_typeTV);
            descTv=itemView.findViewById(R.id.desc_TV);

            forwardButton=itemView.findViewById(R.id.forwardBtn);
            submitButton=itemView.findViewById(R.id.submitBTN);
            videoViewButton=itemView.findViewById(R.id.videoViewButtonId);
            taskSampleBtn=itemView.findViewById(R.id.taskSampleBtn);

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
        void onTaskSubmit(int position);
        void onView(int position,Button button,Button viewButton);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }


}
