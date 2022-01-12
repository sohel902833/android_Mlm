package com.sohelsk.boomcash.HomeFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sohelsk.boomcash.Adapter.MemberShipAdapter;
import com.sohelsk.boomcash.Adapter.TaskListAdapter;
import com.sohelsk.boomcash.ApiCall.UserApi;
import com.sohelsk.boomcash.ChatingActivity;
import com.sohelsk.boomcash.DataModel.MembershipListModel;
import com.sohelsk.boomcash.DataModel.TaskListModel;
import com.sohelsk.boomcash.DataModel.TaskModel;
import com.sohelsk.boomcash.DataModel.User;
import com.sohelsk.boomcash.LocalDb.UserDb;
import com.sohelsk.boomcash.R;
import com.sohelsk.boomcash.RetrofitResponse.MembershipListResponse;
import com.sohelsk.boomcash.RetrofitResponse.RetrofitResponse;
import com.sohelsk.boomcash.RetrofitResponse.TaskListResponse;


public class TaskFragment extends Fragment {


    public TaskFragment() {
        // Required empty public constructor
    }


    MemberShipAdapter memberShipAdapter;
    TaskListAdapter taskListAdapter;
    private ProgressDialog progressDialog;
    private UserApi userApi;
    private TextView facebookTaskTv,tiktakTaskTv;
    private String TASK="Facebook";

    private RecyclerView recyclerView, taskListRecyclerView;
    TaskListModel currentTaskList=null;
    UserDb userDb;
    BottomNavigationView bottomNavigationView;

    private FloatingActionButton msgBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        init(view);

        setTaskTv();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChatingActivity.class));
            }
        });

        facebookTaskTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TASK.equals("Tiktak")){
                    TASK="Facebook";
                    if(currentTaskList!=null){
                        currentTaskList.getMyTasks().clear();
                    }
                    setTaskTv();
                    getTasks();
                }
            }
        });
         tiktakTaskTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TASK.equals("Facebook")){
                    TASK="Tiktak";
                    if(currentTaskList!=null){

                        currentTaskList.getMyTasks().clear();
                    }
                    setTaskTv();
                    getTasks();
                }
            }
        });
        return view;
    }
    private void init(View view) {

        msgBtn=view.findViewById(R.id.messageIconButton);
        bottomNavigationView=getActivity().findViewById(R.id.bottom_navigation);
        userDb=new UserDb(getActivity());
        progressDialog = new ProgressDialog(getContext());
        userApi = new UserApi(getActivity());
        recyclerView = view.findViewById(R.id.membershipListRecyclerViewid);
        taskListRecyclerView = view.findViewById(R.id.taskListRecyclerViewid);
        facebookTaskTv=view.findViewById(R.id.faceBookTaskTvId);
        tiktakTaskTv=view.findViewById(R.id.tiktalkTaskTvId);
        taskListRecyclerView.setHasFixedSize(true);
        taskListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //setup toolbar
        Toolbar toolbar = getActivity().findViewById(R.id.appBarId);
       toolbar.setVisibility(View.VISIBLE);

        TextView appBarTv=toolbar.findViewById(R.id.layoutTextId);
        appBarTv.setText("Tasks");


    }
private  void setTaskTv(){
        if(TASK.equals("Facebook")){
            facebookTaskTv.setBackgroundResource(R.color.tomato);
            facebookTaskTv.setTextColor(getResources().getColor(R.color.white));
            tiktakTaskTv.setBackgroundColor(Color.TRANSPARENT);
            tiktakTaskTv.setTextColor(getResources().getColor(R.color.black));
        }else if(TASK.equals("Tiktak")){
            tiktakTaskTv.setBackgroundResource(R.color.tomato);
            tiktakTaskTv.setTextColor(getResources().getColor(R.color.white));
            facebookTaskTv.setBackgroundColor(Color.TRANSPARENT);
            facebookTaskTv.setTextColor(getResources().getColor(R.color.black));
        }
}

    @Override
    public void onStart() {
        super.onStart();

        getTasks();

    }


    private  void getTasks(){
        progressDialog.setMessage("Loading..");
        userApi.getMyTask(progressDialog, TASK, new TaskListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, TaskListModel taskList) {
                if(currentTaskList!=null){
                    currentTaskList.getMyTasks().clear();

                }
                progressDialog.dismiss();
                currentTaskList=taskList;
                taskListAdapter = new TaskListAdapter(getContext(), currentTaskList);
                taskListRecyclerView.setAdapter(taskListAdapter);

                taskListAdapter.setOnItemClickListner(new TaskListAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onReceiveClick(int position) {
                        progressDialog.setMessage("Receiving Task.");
                        TaskModel task=taskList.getMyTasks().get(position);
                        userApi.receiveTask(progressDialog,String.valueOf(task.getId()), new RetrofitResponse() {
                            @Override
                            public void onSuccess(String message, ProgressDialog progressDialog) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
                                User user=userDb.getUserData();
                                int todayDone=user.getTodayDone()+1;
                                user.setTodayDone(todayDone);
                                userDb.setUserData(user);
                               // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new OrderFragment()).commit();
                                bottomNavigationView.setSelectedItemId(R.id.order_nav_id);
                              // getTasks();
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
                if(currentTaskList!=null){
                    currentTaskList.getMyTasks().clear();
                }
                if(taskListAdapter!=null){
                    taskListAdapter.notifyDataSetChanged();
                }
                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
            }
        });
        userApi.getAllMembershipLists(progressDialog, new MembershipListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, MembershipListModel membershipList2) {
                progressDialog.dismiss();
                memberShipAdapter = new MemberShipAdapter(getActivity(), membershipList2);
                recyclerView.setAdapter(memberShipAdapter);

            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
            }
        });
    }





}