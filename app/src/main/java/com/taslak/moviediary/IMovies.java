package com.taslak.moviediary;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IMovies {
    @GET("/")
    void getMovie(@Query("s") String movieName, Callback<Movies> callbackMovies);
}
