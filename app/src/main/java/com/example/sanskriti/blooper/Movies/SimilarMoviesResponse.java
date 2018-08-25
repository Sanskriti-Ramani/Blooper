package com.example.sanskriti.blooper.Movies;

import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimilarMoviesResponse {

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<AboutMovie> results;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("total_results")
    private Integer totalResults;

    public SimilarMoviesResponse(Integer page, List<AboutMovie> results, Integer totalPages, Integer totalResults) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<AboutMovie> getResults() {
        return results;
    }

    public void setResults(List<AboutMovie> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
