package com.sohelsk.boomcash;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.boomcash.Adapter.ReferUserListAdapter;
import com.sohelsk.boomcash.ApiCall.DataApi;
import com.sohelsk.boomcash.DataModel.GenerationUserListModel;
import com.sohelsk.boomcash.RetrofitResponse.GenerationUserResponse;

public class TeamReportActivity extends AppCompatActivity {

    private RecyclerView recyclerView,gen3RecyclerView,gen2RecyclerView;
    private DataApi dataApi;
    private ProgressDialog progressDialog;
    private ReferUserListAdapter adapter,level2Adapter,level3Adapter;
    private RelativeLayout level1Rl,level2Rl,level3Rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_report);
        init();


        level1Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   enableGen1();
            }
        });
        level2Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   enableGen2();
            }
        });
        level3Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   enableGen3();
            }
        });


    }
    private  void init(){
        level1Rl=findViewById(R.id.gen1Rl);
        level2Rl=findViewById(R.id.gen2Rl);
        level3Rl=findViewById(R.id.gen3Rl);
        recyclerView=findViewById(R.id.referUserRecyclerViewid);
        gen2RecyclerView=findViewById(R.id.gen2RecyclerViewId);
        gen3RecyclerView=findViewById(R.id.gen3RecyclerViewId);
        recyclerView.setHasFixedSize(true);
        gen2RecyclerView.setHasFixedSize(true);
        gen2RecyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gen2RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gen3RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataApi=new DataApi(this);
        progressDialog=new ProgressDialog(this);
        setToolbar("Team Report");
        progressDialog.setMessage("Loading.");

    }

    public void setToolbar(String title){
        Toolbar toolbar = findViewById(R.id.appBarId);
        ImageView backButton = (ImageView) toolbar.findViewById(R.id.backBtn);
        TextView appBarTv = toolbar.findViewById(R.id.mainTvId);
        TextView tv2 = toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        appBarTv.setText(""+title);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private  void enableGen1(){
        gen2RecyclerView.setVisibility(View.GONE);
        gen3RecyclerView.setVisibility(View.GONE);

            if(recyclerView.getVisibility()==View.VISIBLE){
                recyclerView.setVisibility(View.GONE);
            }else{
                recyclerView.setVisibility(View.VISIBLE);
            }


    }

    private  void enableGen2(){
        recyclerView.setVisibility(View.GONE);
        gen3RecyclerView.setVisibility(View.GONE);

            if(gen2RecyclerView.getVisibility()==View.VISIBLE){
                gen2RecyclerView.setVisibility(View.GONE);
            }else{
                gen2RecyclerView.setVisibility(View.VISIBLE);
            }


    }
    private  void enableGen3(){
        recyclerView.setVisibility(View.GONE);
        gen2RecyclerView.setVisibility(View.GONE);

            if(gen3RecyclerView.getVisibility()==View.VISIBLE){
                gen3RecyclerView.setVisibility(View.GONE);
            }else{
                gen3RecyclerView.setVisibility(View.VISIBLE);
            }


    }




    @Override
    protected void onStart() {
        super.onStart();
        gen2RecyclerView.setVisibility(View.GONE);
        gen3RecyclerView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        dataApi.getMyGeneration(progressDialog, new GenerationUserResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, GenerationUserListModel generationList) {
                progressDialog.dismiss();
                if (generationList.getFirstGen() != null) {
                    adapter = new ReferUserListAdapter(TeamReportActivity.this, generationList.getFirstGen());
                    recyclerView.setAdapter(adapter);
                }

                if (generationList.getSecondGen() != null) {
                    level2Adapter = new ReferUserListAdapter(TeamReportActivity.this, generationList.getSecondGen());
                    gen2RecyclerView.setAdapter(level2Adapter);
                }
                if (generationList.getThirdGen() != null) {
                    level3Adapter = new ReferUserListAdapter(TeamReportActivity.this, generationList.getThirdGen());
                    gen3RecyclerView.setAdapter(level3Adapter);
                }


            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(TeamReportActivity.this, "" + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}