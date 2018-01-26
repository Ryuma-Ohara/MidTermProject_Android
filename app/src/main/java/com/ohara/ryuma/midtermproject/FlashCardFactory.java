package com.ohara.ryuma.midtermproject;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by ryuma on 2018-01-22.
 */

public class FlashCardFactory {
    private ArrayList<FlashCard> flashCardSet;
    private Activity context;
    private DatabaseHelper myDb;
    private ArrayList<String> folderIds;

    public FlashCardFactory(Activity context, String id) {
        this.context = context;
        myDb = new DatabaseHelper(context);
        setCardList(id);
    }

//    public ArrayList<FlashCard> getFlashCardSet() {
//        return flashCardSet;
//    }

    public ArrayList<FlashCard> setCardList(String id){
        try {
            Cursor c = myDb.getAllFlashCardDataWithID(id);
            flashCardSet = new ArrayList<>();
            //folderIds = new ArrayList<>();
            if (c.moveToNext()) {
                do {
                    Log.d(TAG, "setCardList: " + c.getString(1));
//                    //folderIds.add(c.getString(3));
//                    flashCardSet.add(new FlashCard(c.getString(1),c.getString(2)));
//                    Log.d("got a cursor2: ", c.getString(0));
                } while (c.moveToNext());
            }
            c.close();
            myDb.close();
        } catch (Exception sqle) {
        }
        return flashCardSet;

    }
}
