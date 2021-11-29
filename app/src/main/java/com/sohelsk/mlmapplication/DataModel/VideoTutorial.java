package com.sohelsk.mlmapplication.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoTutorial {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}