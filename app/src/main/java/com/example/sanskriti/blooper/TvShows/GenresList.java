package com.example.sanskriti.blooper.TvShows;

import com.example.sanskriti.blooper.Movies.Genre;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenresList {
    @SerializedName("genres")
    private List<Genres> genres;

    public GenresList(List<Genres> genres) {
        this.genres = genres;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }
}
