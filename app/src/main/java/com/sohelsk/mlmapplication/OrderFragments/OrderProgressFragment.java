package com.sohelsk.mlmapplication.OrderFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sohelsk.mlmapplication.Adapter.TaskReceiveListAdapter;
import com.sohelsk.mlmapplication.ApiCall.UserApi;
import com.sohelsk.mlmapplication.DataModel.TaskListModel;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.R;
import com.sohelsk.mlmapplication.RetrofitResponse.RetrofitResponse;
import com.sohelsk.mlmapplication.RetrofitResponse.TaskListResponse;


public class OrderProgressFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskReceiveListAdapter taskReceiveListAdapter;
    private ProgressDialog progressDialog;
    private UserApi userApi;
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
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..");
        userApi=new UserApi(getActivity());
        recyclerView=view.findViewById(R.id.taskReceiveListRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        onStart();
    }
    @Override
    public void onStart() {
        super.onStart();
        userApi.getReceiveTaskList(progressDialog, new TaskListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, TaskListModel taskList) {
                    progressDialog.dismiss();
                    taskReceiveListAdapter=new TaskReceiveListAdapter(getContext(),taskList);
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