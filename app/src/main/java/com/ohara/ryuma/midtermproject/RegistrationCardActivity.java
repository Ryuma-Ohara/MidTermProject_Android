package com.ohara.ryuma.midtermproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationCardActivity extends AppCompatActivity {

//    private EditText editCardFront;

    private EditText editCardFront, editCardBack;

    private DatabaseHelper myDb;
    private Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_card);
        myDb = new DatabaseHelper(this);
        editCardFront = (EditText) findViewById(R.id.edit_card_front);
        editCardBack = (EditText) findViewById(R.id.edit_card_back);
        btnAddData = (Button) findViewById(R.id.button_save_card);
        addData();
        // Focus on EditText
//        editCardFront = (EditText)findViewById(R.id.edit_card_front);
//        editCardFront.requestFocus();
//        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    public void addData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertFlashCardData(editCardFront.getText().toString(),
                                editCardBack.getText().toString());

                        if (isInserted == true) {
                            Toast.makeText(RegistrationCardActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegistrationCardActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }

}
