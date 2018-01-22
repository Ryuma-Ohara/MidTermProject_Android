package com.ohara.ryuma.midtermproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class FolderActivity extends AppCompatActivity {

    private Button btnSave;
//    private EditText editFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        // Action bar title of app
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Flash Cards");

        // Move to CardList view
        btnSave = (Button) findViewById(R.id.button_save_folder);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FolderActivity.this, CardListActivity.class);
                startActivity(i);
            }
        });

        // Focus on EditText
//        editFolder = (EditText)findViewById(R.id.edit_folder);
//        editFolder.requestFocus();
//        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);


    }
}
