package com.example.sanskriti.blooper.TvShows;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedTvShowsResponse {
    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<TvShowDetails> results;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;

    public TopRatedTvShowsResponse(Integer page, List<TvShowDetails> results, Integer totalResults, Integer totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<TvShowDetails> getResults() {
        return results;
    }

    public void setResults(List<TvShowDetails> results) {
        this.results = results;
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
