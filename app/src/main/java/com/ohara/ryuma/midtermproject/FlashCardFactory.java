package com.ohara.ryuma.midtermproject;

import java.util.ArrayList;

/**
 * Created by ryuma on 2018-01-22.
 */

public class FlashCardFactory {
    private ArrayList<FlashCard> flashCardSet;

    public FlashCardFactory() {
        flashCardSet = new ArrayList<>();
        flashCardSet.add(new FlashCard("go","to travel or move to another place"));
        flashCardSet.add(new FlashCard("Brown","#A52A2A"));
        flashCardSet.add(new FlashCard("Yellow","#FFFF00"));
        flashCardSet.add(new FlashCard("Orange","#FFA500"));
        flashCardSet.add(new FlashCard("Blue","#0000FF"));
    }

    public ArrayList<FlashCard> getFlashCardSet() {
        return flashCardSet;
    }

}
