package com.example.sanskriti.blooper.Movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCreditsResponse {
    @SerializedName("id")
    private Integer id;
    @SerializedName("cast")
    private List<MovieCast> casts;
    @SerializedName("crew")
    private List<MovieCrew> crews;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieCast> getCasts() {
        return casts;
    }

    public void setCasts(List<MovieCast> casts) {
        this.casts = casts;
    }

    public List<MovieCrew> getCrews() {
        return crews;
    }

    public void setCrews(List<MovieCrew> crews) {
        this.crews = crews;
    }

    public MovieCreditsResponse(Integer id, List<MovieCast> casts, List<MovieCrew> crews) {

        this.id = id;
        this.casts = casts;
        this.crews = crews;
    }
}
