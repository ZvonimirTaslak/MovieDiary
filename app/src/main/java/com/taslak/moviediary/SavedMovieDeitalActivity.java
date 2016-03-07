package com.taslak.moviediary;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SavedMovieDeitalActivity extends AppCompatActivity {

    public static final String SAVED_MOVIE_DITAIL_KEY = "com.taslak.moviediary.saved.movie.detail.key";

    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvRated;
    private TextView tvReleased;
    private TextView tvGenre;
    private TextView tvDirector;
    private TextView tvActors;
    private TextView tvimdbRating;
    private CheckBox cbFavorite;
    private CheckBox cbSeen;

    private String imdbId;

    private TextView tvDescription;


    private SQLiteDatabase db;
    private Cursor cursor;
    private ListView lvAll;
    private ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movie_deital);

        initwidgets();
        handleIntent();
        setupDatabase();
        setuplistener();
    }

    private void setuplistener() {
        cbFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateDB();

            }
        });
        cbSeen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateDB();

            }
        });

        tvDescription.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog();
                return false;
            }
        });
    }

    private void showDialog() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                tvDescription.setText(userInput.getText());
                                updateDB();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void updateDB() {

        try {
            ContentValues values= new ContentValues();
            MoviesDatabaseHelper helper = new MoviesDatabaseHelper(this);
            db = helper.getWritableDatabase();
            values.put(helper.COLUMN_FAVORITE, cbFavorite.isChecked());
            values.put(helper.COLUMN_WATCHED, cbSeen.isChecked());
            values.put(helper.COLUMN_DESCRIPTION, tvDescription.getText().toString());
            db.update(helper.TABLE_NAME,values, helper.COLUMN_ID+"=?",new String[]{imdbId});
        }catch (SQLiteException e){
            Toast.makeText(SavedMovieDeitalActivity.this,R.string.database_unavailable , Toast.LENGTH_SHORT).show();
        }

    }

    private void initwidgets() {
        tvTitle = (TextView) findViewById(R.id.tvTitleS);
        tvRated = (TextView) findViewById(R.id.tvRatedS);
        tvYear = (TextView) findViewById(R.id.tvYearS);
        tvReleased = (TextView) findViewById(R.id.tvReleasedS);
        tvGenre = (TextView) findViewById(R.id.tvGenreS);
        tvDirector = (TextView) findViewById(R.id.tvDirectorS);
        tvActors = (TextView) findViewById(R.id.tvActorsS);
        tvimdbRating = (TextView) findViewById(R.id.tvimdbRatingS);
        cbFavorite = (CheckBox) findViewById(R.id.cbFavorite);
        cbSeen = (CheckBox) findViewById(R.id.cbSeen);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
    }

    private void setupDatabase() {

        try {
            MoviesDatabaseHelper helper = new MoviesDatabaseHelper(this);
            db = helper.getWritableDatabase();
            cursor = db.query(MoviesDatabaseHelper.TABLE_NAME,
                    new String[]{MoviesDatabaseHelper.COLUMN_TITLE,
                            MoviesDatabaseHelper.COLUMN_YEAR,
                            MoviesDatabaseHelper.COLUMN_RATED,
                            MoviesDatabaseHelper.COLUMN_RELASED,
                            MoviesDatabaseHelper.COLUMN_GENRE,
                            MoviesDatabaseHelper.COLUMN_DIRECTOR,
                            MoviesDatabaseHelper.COLUMN_ACTORS,
                            MoviesDatabaseHelper.COLUMN_IMDB,
                            MoviesDatabaseHelper.COLUMN_DESCRIPTION,
                            MoviesDatabaseHelper.COLUMN_FAVORITE,
                            MoviesDatabaseHelper.COLUMN_WATCHED},
                    MoviesDatabaseHelper.COLUMN_ID + " = ? ", new String[]{imdbId},
                    null, null, null);

            cursor.moveToFirst();
            tvTitle.setText(tvTitle.getText() +cursor.getString(0));
            tvYear.setText(tvYear.getText() +cursor.getString(1));
            tvRated.setText(tvRated.getText() +cursor.getString(2));
            tvReleased.setText(tvReleased.getText() +cursor.getString(3));
            tvGenre.setText(tvGenre.getText() +cursor.getString(4));
            tvDirector.setText(tvDirector.getText() +cursor.getString(5));
            tvActors.setText(tvActors.getText() +cursor.getString(6));
            tvimdbRating.setText(tvimdbRating.getText() +cursor.getString(7));
            tvDescription.setText(cursor.getString(8));

            if(cursor.getInt(9)==1){
                cbFavorite.setChecked(true);
            }else {
                cbFavorite.setChecked(false);
            }
            if(cursor.getInt(10)==1){
                cbSeen.setChecked(true);
            }else {
                cbSeen.setChecked(false);
            }

        } catch (SQLiteException e) {
            Toast.makeText(this, R.string.database_unavailable, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        imdbId = intent.getStringExtra(SAVED_MOVIE_DITAIL_KEY);
    }
}
