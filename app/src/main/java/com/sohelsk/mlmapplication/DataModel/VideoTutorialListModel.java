package com.sohelsk.mlmapplication.DataModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoTutorialListModel {

    @SerializedName("videoTutorial")
    @Expose
    private List<VideoTutorial> videoTutorial = null;

    public List<VideoTutorial> getVideoTutorial() {
        return videoTutorial;
    }

    public void setVideoTutorial(List<VideoTutorial> videoTutorial) {
        this.videoTutorial = videoTutorial;
    }

}