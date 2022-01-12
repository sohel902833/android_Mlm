package com.sohelsk.boomcash.OrderFragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sohelsk.boomcash.Adapter.TaskCompleteListAdapter;
import com.sohelsk.boomcash.ApiCall.UserApi;
import com.sohelsk.boomcash.DataModel.TaskListModel;
import com.sohelsk.boomcash.LocalDb.JobViewDb;
import com.sohelsk.boomcash.R;
import com.sohelsk.boomcash.RetrofitResponse.TaskListResponse;


public class OrderFinishedFragment extends Fragment {
    private RecyclerView recyclerView;
    private TaskCompleteListAdapter taskReceiveListAdapter;
    private ProgressDialog progressDialog;
    private UserApi userApi;
    private JobViewDb jobViewDb;


    public OrderFinishedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_order_finished, container, false);
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
        userApi.getCompleteTaskList(progressDialog, new TaskListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, TaskListModel taskList) {
                progressDialog.dismiss();
                taskReceiveListAdapter=new TaskCompleteListAdapter(getActivity(),taskList);
                recyclerView.setAdapter(taskReceiveListAdapter);
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}