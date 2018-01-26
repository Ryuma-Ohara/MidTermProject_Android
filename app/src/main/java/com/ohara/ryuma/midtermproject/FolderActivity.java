package com.ohara.ryuma.midtermproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FolderActivity extends AppCompatActivity {

    private Button btnSave;
    private DatabaseHelper myDb;
    private EditText editName;
    private String newFolderId;

//    private EditText editFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        myDb = new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.edit_folder_name);
        btnSave = (Button) findViewById(R.id.button_save_folder);

        // Action bar back to previous page
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Folder");
        actionBar.setDisplayHomeAsUpEnabled(true);

        addFolderName();

//        String edit = editName.getText().toString();
//        if (edit.matches("")) {
//            btnSave.setEnabled(false);
//        } else {
//            btnSave.setEnabled(true);
//        }


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addFolderName() {
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertFolderData(editName.getText().toString());

//                        if (isInserted == true) {
//                            Toast.makeText(FolderActivity.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(FolderActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
//
//                        }

                        // Move to CardList view
                        Intent i = new Intent(FolderActivity.this, CardListActivity.class);
//                        i.putExtra("NEWID",newFolderId);
                        startActivity(i);

                    }
                }
        );

    }


}
