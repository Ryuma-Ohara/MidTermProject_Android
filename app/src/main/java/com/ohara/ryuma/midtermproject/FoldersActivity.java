package com.ohara.ryuma.midtermproject;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoldersActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private Button btnAddFolder;
    private List<String> folderList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);

        // Action bar to the top
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Flash cards");

        // List for folders
        folderList = new ArrayList<String>(Arrays.asList("Verb1", "Verb2", "Noun1", "Preposition", "adverb", "adjective"));
        arrayAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, folderList);

        lv = (ListView) findViewById(R.id.list_view_folder);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(FoldersActivity.this, MainActivity.class);
                startActivity(i);

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



