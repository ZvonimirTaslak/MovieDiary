package com.taslak.moviediary;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class AllFragment extends android.support.v4.app.Fragment {

    private SQLiteDatabase db;
    private Cursor cursor;
    private ListView lvAll;
    private ArrayList<String> list;
    private ArrayList<String> listId;
    private String idSelected;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all, container, false);
        initWidget(v);
        setupListener();
        registerForContextMenu(lvAll);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupDatabase();
    }

    private void setupListener() {
        lvAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SavedMovieDeitalActivity.class);
                intent.putExtra(SavedMovieDeitalActivity.SAVED_MOVIE_DITAIL_KEY, listId.get(position));
                startActivity(intent);
            }
        });
    }

    private void setupDatabase() {

        try {
            MoviesDatabaseHelper helper = new MoviesDatabaseHelper(getContext());
            db = helper.getReadableDatabase();
            cursor = db.query(MoviesDatabaseHelper.TABLE_NAME,
                    new String[]{MoviesDatabaseHelper.COLUMN_TITLE,
                            MoviesDatabaseHelper.COLUMN_YEAR,
                            MoviesDatabaseHelper.COLUMN_ID},
                    null, null, null, null, null);
            list = new ArrayList<>();
            listId = new ArrayList<>();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                list.add(cursor.getString(0) + "    " + cursor.getString(1));
                listId.add(cursor.getString(2));
            }

            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list);

            lvAll.setAdapter(adapter);
        } catch (SQLiteException e) {
            Toast.makeText(getContext(), R.string.database_unavailable, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cursor.close();
        db.close();
    }

    private void initWidget(View v) {
        lvAll = (ListView) v.findViewById(R.id.lvAll);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(R.string.delete_movie);
        MenuInflater inflater= getActivity().getMenuInflater();
        inflater.inflate(R.menu.delete, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(getContext(), item.getGroupId()+"", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.cancel:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
