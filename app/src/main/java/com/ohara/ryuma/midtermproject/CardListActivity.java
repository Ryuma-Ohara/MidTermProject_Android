package com.ohara.ryuma.midtermproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;

public class CardListActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<String> items;
    private ArrayList<String> frontItem;
    private ArrayList<String> backItem;
    private ArrayList<String> delItem;
    private DatabaseHelper myDb;
    private ArrayAdapter<String> adapter;
    private Button addCardBtn;
    private ImageButton delBtn;
    private boolean delMode = false;
    private String frontVal;
    private String backVal;
    private String folderExtraId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);


        // Action bar to the top
        Intent i = getIntent();
        String title = i.getStringExtra("TITLE");
        folderExtraId = i.getStringExtra("FOLDER_ID");
        Log.d( "FOLDER_ID:  " ,"IDid:" + folderExtraId);

        String newFolderId = i.getStringExtra("NEWID");
        Log.d( "FOLDER_ID:  " ,"folderId:" + newFolderId);

        String className = i.getStringExtra("CLASSNAME");
        Log.d( "FOLDER_ID:  " ,"className:" + className);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

        lv = (ListView) findViewById(R.id.list_view_card);
        items = new ArrayList<>();
        frontItem = new ArrayList<>();
        backItem = new ArrayList<>();
        delItem = new ArrayList<>();
        myDb = new DatabaseHelper(this); // add

        try {
            Cursor c = myDb.getAllFlashCardDataWithID(folderExtraId);
            if (c.moveToNext()) {
                do {
                    items.add(c.getString(1) + " / " + c.getString(2));
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!delMode) {

                    try {
                        Cursor c = myDb.getAllFlashCardDataWithID(folderExtraId);
                        if (c.moveToNext()) {
                            do {
                                frontItem.add(c.getString(1));
                                backItem.add(c.getString(2));
                                Log.d("got a cursor: ", c.getString(1));
                            } while (c.moveToNext());
                        }
                        frontVal = frontItem.get(position);
                        backVal = backItem.get(position);

                        c.close();
                        myDb.close();

                    } catch (Exception sqle) {}

                    Intent i = new Intent(CardListActivity.this, RegistrationCardActivity.class);
                    i.putExtra("FRONT",frontVal);
                    i.putExtra("BACK",backVal);
                    i.putExtra("FOLDER_ID2", folderExtraId);
                    startActivity(i);
                } else {
                    try {
                        Cursor c = myDb.getAllFlashCardDataWithID(folderExtraId);
                        if (c.moveToNext()) {
                            do {
                                delItem.add(c.getString(0));
                                Log.d("got a cursor: ", c.getString(0));
                            } while (c.moveToNext());
                        }
                        items.remove(position);
                        myDb.deleteFlashCardData(delItem.get(position));
                        adapter.notifyDataSetChanged();
                        c.close();
                        myDb.close();

                    } catch (Exception sqle) {
                    }

                }
            }
        });

        // Plus button
        addCardBtn = (Button) findViewById(R.id.add_card);
        addCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CardListActivity.this, RegistrationCardActivity.class);
                i.putExtra("FOLDER_ID2", folderExtraId);
                startActivity(i);
            }
        });

        // Delete button
        delBtn = (ImageButton) findViewById(R.id.del_card);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!delMode){
                    delMode = true;
                    delBtn.setBackgroundColor(Color.rgb(200, 0, 0));
                } else {
                    delMode = false;
                    delBtn.setBackgroundColor(Color.rgb(204, 204, 204));
                }
            }
        });
    }

    // when user return to this page, adapter will be refreshed
    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

    }
}
