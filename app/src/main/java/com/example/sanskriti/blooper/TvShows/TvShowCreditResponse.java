package com.example.sanskriti.blooper.TvShows;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowCreditResponse {
    @SerializedName("cast")
    private List<TvShowCast> casts;
    @SerializedName("crew")
    private List<TvShowCrew> crews;
    @SerializedName("id")
    private Integer id;

    public TvShowCreditResponse(List<TvShowCast> casts, List<TvShowCrew> crews, Integer id) {
        this.casts = casts;
        this.crews = crews;
        this.id = id;
    }

    public List<TvShowCast> getCasts() {
        return casts;
    }

    public void setCasts(List<TvShowCast> casts) {
        this.casts = casts;
    }

    public List<TvShowCrew> getCrews() {
        return crews;
    }

    public void setCrews(List<TvShowCrew> crews) {
        this.crews = crews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
