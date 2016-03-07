package com.taslak.moviediary;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_DITAIL_KEY = "com.taslak.moviediary.movie.detail.key";

    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvRated;
    private TextView tvReleased;
    private TextView tvGenre;
    private TextView tvDirector;
    private TextView tvActors;
    private TextView tvimdbRating;
    private Button btnSave;

    private String imdbId;

    private IMovie iMovie;
    private Callback<Movie> callback;
    private Movie movieCopy;

    private MoviesDatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initwidgets();
        setupRestAdapter();
        handleIntent();
        setupListener();


    }

    private void setupListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void insertData() {
        ContentValues values = new ContentValues();
        values.put(MoviesDatabaseHelper.COLUMN_ID, imdbId);
        values.put(MoviesDatabaseHelper.COLUMN_TITLE, movieCopy.getTitle());
        values.put(MoviesDatabaseHelper.COLUMN_YEAR, movieCopy.getYear());
        values.put(MoviesDatabaseHelper.COLUMN_RELASED, movieCopy.getReleased());
        values.put(MoviesDatabaseHelper.COLUMN_GENRE, movieCopy.getGenre());
        values.put(MoviesDatabaseHelper.COLUMN_DIRECTOR, movieCopy.getDirector());
        values.put(MoviesDatabaseHelper.COLUMN_ACTORS, movieCopy.getActors());
        values.put(MoviesDatabaseHelper.COLUMN_IMDB, movieCopy.getImdbRating());

        helper = new MoviesDatabaseHelper(this);
        db = helper.getWritableDatabase();
        Long insert = db.insert(MoviesDatabaseHelper.TABLE_NAME, null, values);
        db.close();
        if (insert != -1) {
            Toast.makeText(MovieDetailActivity.this, "Data insert", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(MovieDetailActivity.this, "Noooooooooooo", Toast.LENGTH_SHORT).show();
        }


    }

    private void initwidgets() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvRated = (TextView) findViewById(R.id.tvRated);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvReleased = (TextView) findViewById(R.id.tvReleased);
        tvGenre = (TextView) findViewById(R.id.tvGenre);
        tvDirector = (TextView) findViewById(R.id.tvDirector);
        tvActors = (TextView) findViewById(R.id.tvActors);
        tvimdbRating = (TextView) findViewById(R.id.tvimdbRating);

        btnSave = (Button) findViewById(R.id.btnSaveMovie);
    }

    private void setupRestAdapter() {
        iMovie = RestAdapter.getAdapter().create(IMovie.class);
        callback = new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {
                movieCopy = movie;
                tvTitle.setText(movie.getTitle());
                tvYear.setText(tvYear.getText() + movie.getYear());
                tvRated.setText(tvRated.getText() + movie.getRated());
                tvReleased.setText(tvReleased.getText() + movie.getReleased());
                tvGenre.setText(tvGenre.getText() + movie.getGenre());
                tvDirector.setText(tvDirector.getText() + movie.getDirector());
                tvActors.setText(tvActors.getText() + movie.getActors());
                tvimdbRating.setText(tvimdbRating.getText() + movie.getImdbRating());

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MovieDetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        };
    }

    private void handleIntent() {
        Intent intent = getIntent();
        imdbId = intent.getStringExtra(MOVIE_DITAIL_KEY);
        iMovie.getMovie(imdbId, callback);
    }


}
