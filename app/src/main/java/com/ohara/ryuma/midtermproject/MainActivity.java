package com.ohara.ryuma.midtermproject;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    private int currentCard = 0;
    private ArrayList<FlashCard> card;
    private DatabaseHelper myDb;
    private ArrayList<String> items;
    private ImageButton arrowLeft;
    private ImageButton arrowRight;
    private ImageButton arrowFirst;
    private ImageButton arrowFinal;
    private TextView numCard;
    private ArrayList<FlashCard> flashCardSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String id = i.getStringExtra("ID");
        String title = i.getStringExtra("TITLE");

        // Action bar title of app
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

        DatabaseHelper db = new DatabaseHelper(this);

        try {
            Cursor c = db.getAllFlashCardDataWithID(id);  // debug
            card = new ArrayList<>();
            if (c.moveToNext()) {
                do {
                    card.add(new FlashCard(c.getString(1), c.getString(2)));
                    Log.d(TAG, "ID: " + c.getString(0));
                    Log.d(TAG, "FRONT: " + c.getString(1));
                    Log.d(TAG, "BACK: " + c.getString(2));
                    Log.d(TAG, "FOLDER_ID: " + c.getString(3));
                } while (c.moveToNext());
            }
            c.close();
            myDb.close();
        } catch (Exception sqle) {
        }

        setFlashCard();

        findViews();
        loadAnimations();
        changeCameraDistance();

        arrowRight = (ImageButton) findViewById(R.id.arrow_right);
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentCard < card.size() - 1){
                    currentCard++;
                    mIsBackVisible = true;
                    flipCard(view);
                    setFlashCard();
                } else {
                    Toast.makeText(MainActivity.this, "NO MORE CARDS...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        arrowLeft = (ImageButton) findViewById(R.id.arrow_left);
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (0 < currentCard ){
                    currentCard--;
                    mIsBackVisible = true;
                    flipCard(view);
                    setFlashCard();
                } else {
                    Toast.makeText(MainActivity.this, "NO MORE CARDS...", Toast.LENGTH_SHORT).show();
                }

            }
        });

        arrowFirst = (ImageButton) findViewById(R.id.arrow_first);
        arrowFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    currentCard = 0;
                    mIsBackVisible = true;
                    flipCard(view);
                    setFlashCard();
            }
        });

        arrowFinal = (ImageButton) findViewById(R.id.arrow_final);
        arrowFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    currentCard = card.size()-1;
                    mIsBackVisible = true;
                    flipCard(view);
                    setFlashCard();
            }
        });
    }

    //========== Set Flash cards ==========
    public void setFlashCard() {
        TextView flashCardFrontView = (TextView) findViewById(R.id.card_word);
        flashCardFrontView.setText(card.get(currentCard).getFront());

        TextView flashCardBackView = (TextView) findViewById(R.id.card_meaning);
        flashCardBackView.setText(card.get(currentCard).getBack());

        numCard = (TextView) findViewById(R.id.card_num);
        numCard.setText((currentCard + 1) + "/" + card.size());

    }

    //========== Animation ==========
    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
    }

    private void findViews() {
        mCardBackLayout = findViewById(R.id.card_back);
        mCardFrontLayout = findViewById(R.id.card_front);
    }

    public void flipCard(View view) {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }
}