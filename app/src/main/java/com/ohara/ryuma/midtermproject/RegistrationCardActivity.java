package com.ohara.ryuma.midtermproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrationCardActivity extends AppCompatActivity {

//    private static final String TAG = "RegistrationCardActivity";


    private EditText editCardFront, editCardBack;
    private DatabaseHelper myDb;
    private Button btnAddData;
    private ArrayList<String> items;
    private String folderExtraId;
    private ArrayList<FlashCard> flashCardSet;
//    private int id2;
//    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_card);
        // Action bar back to previous page
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Card");
        actionBar.setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);
        editCardFront = (EditText) findViewById(R.id.edit_card_front);
        editCardBack = (EditText) findViewById(R.id.edit_card_back);
        btnAddData = (Button) findViewById(R.id.button_save_card);
        items = new ArrayList<>();

        // set editText default value
        Intent i = getIntent();
        String frontVal = i.getStringExtra("FRONT");
        Log.d( "FOLDER_ID:  " ,"FrontFront:" + frontVal);
        //Log.d( "FOLDER_ID:  " ,"IDid:" + folderExtraId);


        String backVal = i.getStringExtra("BACK");
        Log.d( "FOLDER_ID:  " ,"BackBack:" + backVal);

        folderExtraId = i.getStringExtra("FOLDER_ID2");
        Log.d( "FOLDER_ID:  " ,"IDidID:" + folderExtraId);

        editCardFront.setText(frontVal);
        editCardBack.setText(backVal);

        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String frontEdit = editCardFront.getText().toString();
                        String backEdit = editCardBack.getText().toString();

                        if (frontEdit.matches("") && backEdit.matches("")) {
                            Toast.makeText(RegistrationCardActivity.this, "Please enter a word and a meaning", Toast.LENGTH_SHORT).show();
                        } else if (backEdit.matches("")) {
                            Toast.makeText(RegistrationCardActivity.this, "Please enter a meaning", Toast.LENGTH_SHORT).show();
                        } else if (frontEdit.matches("")) {
                            Toast.makeText(RegistrationCardActivity.this, "Please enter a word", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean isInserted = myDb.insertFlashCardData(editCardFront.getText().toString(),
                                    editCardBack.getText().toString(), folderExtraId);
                            if (isInserted == true) {
                                Toast.makeText(RegistrationCardActivity.this, "Saved", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                }
        );

    }

//    public void addData() {
//        btnAddData.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        String frontEdit = editCardFront.getText().toString();
//                        String backEdit = editCardBack.getText().toString();
//                        String id = folderExtraId;
//
//                        if (frontEdit.matches("") && backEdit.matches("")) {
//                            Toast.makeText(RegistrationCardActivity.this, "Please enter a word and a meaning", Toast.LENGTH_SHORT).show();
//                        } else if (backEdit.matches("")) {
//                            Toast.makeText(RegistrationCardActivity.this, "Please enter a meaning", Toast.LENGTH_SHORT).show();
//                        } else if (frontEdit.matches("")) {
//                                Toast.makeText(RegistrationCardActivity.this, "Please enter a word", Toast.LENGTH_SHORT).show();
//                        } else {
//                            boolean isInserted = myDb.insertFlashCardData(editCardFront.getText().toString(),
//                                    editCardBack.getText().toString(), id);
//                        }
//
//                    }
//                }
//        );
//    }

}
// Focus on EditText
//        editCardFront = (EditText)findViewById(R.id.edit_card_front);
//        editCardFront.requestFocus();
//        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);

