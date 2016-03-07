package com.taslak.moviediary;


public class RestAdapter {
    private static final String ENDPOINT="http://www.omdbapi.com";

    public static final retrofit.RestAdapter getAdapter() {

        return  new retrofit.RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();
    }
}
