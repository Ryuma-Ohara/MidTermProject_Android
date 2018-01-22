package com.ohara.ryuma.midtermproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardListActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private List<String> folderList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        // List for folders
        folderList = new ArrayList<String>(Arrays.asList("Verb1", "Verb2", "Noun1", "Preposition", "adverb", "adjective"));
        arrayAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, folderList);

        lv = (ListView) findViewById(R.id.list_view_folder);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CardListActivity.this, RegistrationCardActivity.class);
                startActivity(i);

            }
        });


    }
}
