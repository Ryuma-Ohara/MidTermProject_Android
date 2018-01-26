package com.ohara.ryuma.midtermproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoldersActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private Button btnAddFolder;
    private ListView lv;
    private DatabaseHelper myDb;
    private ArrayList<String> items;
    private ArrayList<String> idItems;
    private ArrayList<String> delItem;
    private ImageButton btnDel;
    private ImageButton btnEdit;
    private boolean editMode = false;
    private boolean delMode = false;
    private String folderId;
    private String titleName;
    private String folderExtraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);
        // Action bar to the top
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Flash Cards");

        lv = (ListView) findViewById(R.id.list_view_folder);
        items = new ArrayList<>();
        idItems = new ArrayList<>();
        myDb = new DatabaseHelper(this);
        delItem = new ArrayList<>();


        // set folder list
        try {
              Cursor c = myDb.getAllFolderData();
            if (c.moveToNext()) {
                do {
                    items.add(c.getString(1));
                    Log.d("got a cursor: ", c.getString(1));
                } while (c.moveToNext());
            }
            adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, items);
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            c.close();
            myDb.close();

        } catch (Exception sqle) {
        }

        btnEdit = (ImageButton) findViewById(R.id.edit_folder);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!delMode) {
                    if (!editMode) {
                        editMode = true;
                        btnEdit.setBackgroundColor(Color.rgb(0, 100, 200));
                    } else {
                        editMode = false;
                        btnEdit.setBackgroundColor(Color.rgb(204, 204, 204));
                    }
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (!delMode) {
                    if (!editMode) {

                        // get id from Folder table
                        try {
                            Cursor c = myDb.getAllFolderData();
                            if (c.moveToNext()) {
                                do {
                                    idItems.add(c.getString(0));
                                    items.add(c.getString(1));
                                    Log.d("got a cursor5: ", c.getString(0));
                                } while (c.moveToNext());
                            }
                            folderId = idItems.get(position);
                            Log.d("Folder: " ,"id:" + folderId);
                            titleName = items.get(position);
                            Log.d("Title: " ,"name:" + titleName);
                            c.close();
                            myDb.close();

                        } catch (Exception sqle) {}

                            Intent i = new Intent(FoldersActivity.this, MainActivity.class);
                            i.putExtra("ID", folderId);
                            i.putExtra("TITLE", titleName);
                            startActivity(i);
                    } else {

                        // get id from Folder table
                        try {
                            Cursor c = myDb.getAllFolderData();
                            if (c.moveToNext()) {
                                do {
                                    idItems.add(c.getString(0));
                                    Log.d("got a cursor id: ", c.getString(0));
                                    items.add(c.getString(1));
                                    Log.d("got a cursor items: ", c.getString(1));
                                } while (c.moveToNext());
                            }
                            folderExtraId = idItems.get(position);
                            Log.d("Folder: " ,"id:" + folderExtraId);
                            titleName = items.get(position);
                            Log.d("Title: " ,"name:" + titleName);

                            c.close();
                            myDb.close();

                        } catch (Exception sqle) {}

                        Intent i = new Intent(FoldersActivity.this, CardListActivity.class);
                        i.putExtra("FOLDER_ID", folderExtraId);
                        i.putExtra("TITLE",titleName);
                        startActivity(i);
                    }
                } else if (delMode && !editMode){
                    try {
                        Cursor c = myDb.getAllFolderData();
                        if (c.moveToNext()) {
                            do {
                                delItem.add(c.getString(0));
                                Log.d("got a cursor: ", c.getString(0));
                            } while (c.moveToNext());
                        }
                        myDb.deleteFolderData(delItem.get(position));
                        items.remove(position);
                        adapter.notifyDataSetChanged();
                        c.close();
                        myDb.close();

                    } catch (Exception sqle) {
                    }
                }
            }
        });

        // Delete button
        btnDel = (ImageButton) findViewById(R.id.del_folder);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editMode) {
                    if (!delMode){
                        delMode = true;
                        btnDel.setBackgroundColor(Color.rgb(200, 0, 0));
                    } else {
                        delMode = false;
                        btnDel.setBackgroundColor(Color.rgb(204, 204, 204));
                    }
                }
            }
        });

        // Plus button
        btnAddFolder = (Button) findViewById(R.id.add_folder);
        btnAddFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoldersActivity.this, FolderActivity.class);
                startActivity(i);
            }
        });
    }

    // Search the folder from list
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}



