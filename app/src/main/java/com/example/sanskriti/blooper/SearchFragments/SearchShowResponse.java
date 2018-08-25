package com.example.sanskriti.blooper.SearchFragments;

import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.TvShows.TvShowDetails;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchShowResponse {

    @SerializedName("results")
    private List<TvShowDetails> results;
    @SerializedName("page")
    private Integer page;
    @SerializedName("total_results")
    private Integer totalResults;
    //dates missing
    @SerializedName("total_pages")
    private Integer totalPages;

    public SearchShowResponse(List<TvShowDetails> results, Integer page, Integer totalResults, Integer totalPages) {
        this.results = results;
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public List<TvShowDetails> getResults() {
        return results;
    }

    public void setResults(List<TvShowDetails> results) {
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
