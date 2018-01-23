package com.ohara.ryuma.midtermproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FolderActivity extends AppCompatActivity {

    private Button btnSave;
    private DatabaseHelper myDb;
    private EditText editName;

//    private EditText editFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        myDb = new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.edit_folder_name);
        btnSave = (Button) findViewById(R.id.button_save_folder);

        // Action bar title of app
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Flash Cards");

        addFolderName();

    }

    public void addFolderName() {
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertFolderData(editName.getText().toString());

                        if (isInserted == true) {
                            Toast.makeText(FolderActivity.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FolderActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();

                        }

                        // Move to CardList view
                        Intent i = new Intent(FolderActivity.this, CardListActivity.class);
                        startActivity(i);

                    }
                }
        );

    }




}
