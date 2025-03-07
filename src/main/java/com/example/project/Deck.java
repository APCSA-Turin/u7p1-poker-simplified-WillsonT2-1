package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void initializeDeck(){ //hint.. use the utility class
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 13; j++){
                cards.add(new Card(Utility.getRanks()[j], Utility.getSuits()[i]));
            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public Card drawCard(){
       return cards.remove(0);
    }

    public  boolean isEmpty(){
        return cards.isEmpty();
    }

   


}