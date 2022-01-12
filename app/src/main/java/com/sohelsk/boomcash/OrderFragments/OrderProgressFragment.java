package com.sohelsk.boomcash.OrderFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sohelsk.boomcash.Adapter.TaskReceiveListAdapter;
import com.sohelsk.boomcash.ApiCall.UserApi;
import com.sohelsk.boomcash.DataModel.TaskListModel;
import com.sohelsk.boomcash.DataModel.TaskModel;
import com.sohelsk.boomcash.LocalDb.JobViewDb;
import com.sohelsk.boomcash.R;
import com.sohelsk.boomcash.RetrofitResponse.RetrofitResponse;
import com.sohelsk.boomcash.RetrofitResponse.TaskListResponse;


public class OrderProgressFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskReceiveListAdapter taskReceiveListAdapter;
    private ProgressDialog progressDialog;
    private UserApi userApi;
    private JobViewDb jobViewDb;
    public OrderProgressFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_order_progress, container, false);
        init(view);

        return  view;
    }
    private void init(View view){
        jobViewDb=new JobViewDb(getActivity());
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..");
        userApi=new UserApi(getActivity());
        recyclerView=view.findViewById(R.id.taskReceiveListRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    @Override
    public void onStart() {
        super.onStart();
        userApi.getReceiveTaskList(progressDialog, new TaskListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, TaskListModel taskList) {
                    progressDialog.dismiss();
                    taskReceiveListAdapter=new TaskReceiveListAdapter(getActivity(),taskList);
                    recyclerView.setAdapter(taskReceiveListAdapter);


                    taskReceiveListAdapter.setOnItemClickListner(new TaskReceiveListAdapter.OnItemClickListner() {
                        @Override
                        public void onItemClick(int position) {

                        }

                        @Override
                        public void onTaskSubmit(int position) {

                            progressDialog.setMessage("Completing Task");
                            userApi.completeTask(progressDialog, String.valueOf(taskList.getMyTasks().get(position).getId()), new RetrofitResponse() {
                                @Override
                                public void onSuccess(String message, ProgressDialog progressDialog) {
                                    jobViewDb.clearJobView();
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
                                    onStart();
                                }

                                @Override
                                public void onError(String message, ProgressDialog progressDialog) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onView(int position, Button submitButton,Button viewButton) {
                            try{
                                TaskModel taskModel=taskList.getMyTasks().get(position);
                                jobViewDb.setJobView(taskModel.getId());
                                Uri uri = Uri.parse(taskModel.getVideoUrl());
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);

                                new CountDownTimer(JobViewDb.DELAYTIME, 1000){
                                    public void onTick(long millisUntilFinished){
                                    }
                                    public  void onFinish(){
                                            submitButton.setVisibility(View.VISIBLE);
                                            viewButton.setVisibility(View.GONE);
                                    }
                                }.start();
                            }catch (Exception err){

                            }
                        }
                    });


            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}