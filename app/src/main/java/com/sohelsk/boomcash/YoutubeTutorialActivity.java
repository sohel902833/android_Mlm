package com.sohelsk.boomcash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.sohelsk.boomcash.Adapter.VideoTutorialListAdapter;
import com.sohelsk.boomcash.ApiCall.DataApi;
import com.sohelsk.boomcash.DataModel.VideoTutorialListModel;
import com.sohelsk.boomcash.RetrofitResponse.VideoTutorialListResponse;

public class YoutubeTutorialActivity extends AppCompatActivity {
    YouTubePlayerView youTubePlayerView;
    private RecyclerView recyclerView;

    private ProgressDialog progressDialog;
    private DataApi dataApi;
    VideoTutorialListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_tutorial);
        init();
      //  getLifecycle().addObserver(youTubePlayerView);

    }

    private  void init(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        dataApi=new DataApi(this);
        recyclerView=findViewById(R.id.tutorialRecyclerViewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Toolbar toolbar = findViewById(R.id.appBarId);

        ImageView backButton = (ImageView) toolbar.findViewById(R.id.backBtn);
        TextView appBarTv = toolbar.findViewById(R.id.mainTvId);
        TextView tv2 = toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        appBarTv.setText("Video Tutorial");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        dataApi.getVideoTutorialList(progressDialog, new VideoTutorialListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, VideoTutorialListModel tutorialList) {
                  progressDialog.dismiss();
                    adapter=new VideoTutorialListAdapter(YoutubeTutorialActivity.this,tutorialList);
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(YoutubeTutorialActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}