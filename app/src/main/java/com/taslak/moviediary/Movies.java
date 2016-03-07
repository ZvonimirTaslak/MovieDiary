package com.taslak.moviediary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movies {

    @SerializedName("Search")
    @Expose
    private List<com.taslak.moviediary.Search> Search = new ArrayList<com.taslak.moviediary.Search>();
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String Response;

    /**
     * @return The Search
     */
    public List<com.taslak.moviediary.Search> getSearch() {
        return Search;
    }

    /**
     * @param Search The Search
     */
    public void setSearch(List<com.taslak.moviediary.Search> Search) {
        this.Search = Search;
    }

    /**
     * @return The totalResults
     */
    public String getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The totalResults
     */
    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return The Response
     */
    public String getResponse() {
        return Response;
    }

    /**
     * @param Response The Response
     */
    public void setResponse(String Response) {
        this.Response = Response;
    }

}