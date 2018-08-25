package com.example.sanskriti.blooper.Videos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosResponse {
    @SerializedName("id")
    private Integer id;
    @SerializedName("results")
    private List<Videos> videos;

    public VideosResponse(Integer id, List<Videos> videos) {
        this.id = id;
        this.videos = videos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Videos> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }
}
