package com.taslak.moviediary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MoviesDatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=2;
    private static final String DB_NAME="movies.db";

    public static final String TABLE_NAME = "movie";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_RUNTIME = "runtime";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_ACTORS = "actors";
    public static final String COLUMN_RATED = "rated";
    public static final String COLUMN_IMDB = "imdb";
    public static final String COLUMN_RELASED = "relased";

    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FAVORITE="favorite";
    public static final String COLUMN_WATCHED = "watched";




    private static final String DROP_TABLE = "drop table " + TABLE_NAME;
    private static String CREATE_TABLE = null;

    private static String prepareSqlInsertStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("create table " + TABLE_NAME + " (");
        sb.append(COLUMN_ID + " text primary key, ");
        sb.append(COLUMN_TITLE + " varchar(20), ");
        sb.append(COLUMN_DESCRIPTION + " text, ");
        sb.append(COLUMN_DIRECTOR + " varchar(50), ");
        sb.append(COLUMN_ACTORS + " varchar(50), ");
        sb.append(COLUMN_RUNTIME + " integer, ");
        sb.append(COLUMN_GENRE + " varchar(50), ");
        sb.append(COLUMN_YEAR + " integer, ");
        sb.append(COLUMN_RELASED + " varchar(20), ");
        sb.append(COLUMN_IMDB + " varchar(10), ");
        sb.append(COLUMN_WATCHED + " integer, ");
        sb.append(COLUMN_RATED + " integer, ");
        sb.append(COLUMN_FAVORITE + " integer )");
        return sb.toString();
    }

    static {
        CREATE_TABLE = prepareSqlInsertStatement();
    }

    public MoviesDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
