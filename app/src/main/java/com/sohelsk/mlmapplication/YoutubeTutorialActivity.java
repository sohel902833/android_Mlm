package com.sohelsk.mlmapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.sohelsk.mlmapplication.Adapter.VideoTutorialListAdapter;
import com.sohelsk.mlmapplication.ApiCall.DataApi;
import com.sohelsk.mlmapplication.DataModel.VideoTutorialListModel;
import com.sohelsk.mlmapplication.RetrofitResponse.VideoTutorialListResponse;

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
        youTubePlayerView = findViewById(R.id.youtubePlayerId);
        recyclerView=findViewById(R.id.tutorialRecyclerViewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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