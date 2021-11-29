package com.sohelsk.mlmapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.sohelsk.mlmapplication.DataModel.VideoTutorial;
import com.sohelsk.mlmapplication.DataModel.VideoTutorialListModel;
import com.sohelsk.mlmapplication.R;

public class VideoTutorialListAdapter extends RecyclerView.Adapter<VideoTutorialListAdapter.MyViewHolder>{

    private Activity context;
    private VideoTutorialListModel videoList;
    private  OnItemClickListner listner;

    public VideoTutorialListAdapter(Activity context, VideoTutorialListModel videoList) {

        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.youtube_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VideoTutorial tutorial=videoList.getVideoTutorial().get(position);
        holder.youTubePlayerView.initialize(new YouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = tutorial.getVideoUrl();
                youTubePlayer.loadVideo(videoId, 0);
            }

            @Override
            public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState playerState) {

            }

            @Override
            public void onPlaybackQualityChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackQuality playbackQuality) {

            }

            @Override
            public void onPlaybackRateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackRate playbackRate) {

            }

            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError playerError) {

            }

            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoDuration(YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoLoadedFraction(YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoId(YouTubePlayer youTubePlayer, String s) {

            }

            @Override
            public void onApiChange(YouTubePlayer youTubePlayer) {

            }
        },true);


    }

    @Override
    public int getItemCount() {
        return videoList.getVideoTutorial().size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        YouTubePlayerView youTubePlayerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            youTubePlayerView=itemView.findViewById(R.id.youtubePlayerId);
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
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }


}
