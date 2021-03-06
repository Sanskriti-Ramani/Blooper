package com.example.sanskriti.blooper.Movies;

import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowShowingMoviesResponse {
    @SerializedName("results")
    private List<AboutMovie> results;
    @SerializedName("page")
    private Integer page;
    @SerializedName("total_results")
    private Integer totalResults;
    //dates missing
    @SerializedName("total_pages")
    private Integer totalPages;

    public NowShowingMoviesResponse(List<AboutMovie> results, Integer page, Integer totalResults, Integer totalPages) {
        this.results = results;
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public List<AboutMovie> getResults() {
        return results;
    }

    public void setResults(List<AboutMovie> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
