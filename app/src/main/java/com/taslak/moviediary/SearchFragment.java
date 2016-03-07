package com.taslak.moviediary;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SearchFragment extends Fragment {

    private Button btnGetMovies;
    private EditText etgetMovies;
    private ListView listView;

    private Callback<Movies> callbackMovies;
    private IMovies iMovies;

    private ArrayList<Search> movieList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        initWidgets(v);
        setupListener();
        setupRestAdapter();
        return v;


    }

    private void setupRestAdapter() {
        iMovies = RestAdapter.getAdapter().create(IMovies.class);
        callbackMovies = new Callback<Movies>() {
            @Override
            public void success(Movies movies, Response response) {
                movieList = new ArrayList<>(movies.getSearch());
                showList();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        };


    }

    private void showList() {
        ArrayAdapter<Search> arrayAdapter = new ArrayAdapter<Search>(getContext(),
                android.R.layout.simple_list_item_1,
                movieList);
        listView.setAdapter(arrayAdapter);

    }

    private void setupListener() {
        btnGetMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovies(etgetMovies.getText().toString());

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_DITAIL_KEY,movieList.get(position).getImdbID());
                startActivity(intent);
            }
        });
    }

    private void getMovies(String movie) {
        iMovies.getMovie(movie, callbackMovies);
    }

    private void initWidgets(View v) {
        btnGetMovies = (Button) v.findViewById(R.id.btnGetMovies);
        etgetMovies = (EditText) v.findViewById(R.id.etGetMovies);
        listView = (ListView) v.findViewById(R.id.listView);
    }



}
