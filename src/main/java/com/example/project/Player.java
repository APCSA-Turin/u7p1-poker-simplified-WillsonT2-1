package com.example.project;
import java.util.ArrayList;
import java.util.List;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    private int highAllCard = 0;
    private int highHandCard = 0;
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}
    public int getHighHandCard(){return highHandCard;}
    public int getHighAllCard(){return highAllCard;}

    public void addCard(Card c){
        hand.add(c);
    }
    
    public boolean checkFourOfAKind(){
        ArrayList<Integer> rankingFrequency = findRankingFrequency();
        for (int i = 0; i < rankingFrequency.size(); i++){
            if (rankingFrequency.get(i) == 4){
                return true;
            }
        }
        return false;
    }

    public boolean checkThreeOfAKind(){
        ArrayList<Integer> rankingFrequency = findRankingFrequency();
        for (int i = 0; i < rankingFrequency.size(); i++){
            if (rankingFrequency.get(i) == 3){
                return true;
            }
        }
        return false;
    }

    public boolean checkPair(){
        ArrayList<Integer> rankingFrequency = findRankingFrequency();
        for (int i = 0; i < rankingFrequency.size(); i++){
            if (rankingFrequency.get(i) == 2){
                return true;
            }
        }
        return false;
    }

    public boolean checkFlush(){
        ArrayList<Integer> suitFrequency = findSuitFrequency();
        for (int i = 0; i < suitFrequency.size(); i++){
            if (suitFrequency.get(i) == 5){
                return true;
            }
        }
        return false;
    }

    public boolean checkStraight(){
        ArrayList<Integer> rankingFrequency = findRankingFrequency();
        int counter = 0;
        for (int i = 0; i < rankingFrequency.size(); i++){
            if (rankingFrequency.get(i) == 1){
                counter++;
                if (counter == 5){
                    return true;
                }
            }else {
                counter = 0;
            }
        }
        return false;
    }

    public boolean checkTwoPair(){
        boolean firstPair = false;
        boolean secondPair = false;
        ArrayList<Integer> rankingFrequency = findRankingFrequency();
        for (int i = 0; i < rankingFrequency.size(); i++){
            if (!(firstPair) && rankingFrequency.get(i) == 2){
                firstPair = true;
            }else if (firstPair && !(secondPair) && rankingFrequency.get(i) == 2){
                secondPair = true;
            }
        }
        return firstPair && secondPair;
    }


    public boolean checkHighCard(){
        int highHandCard = Utility.getRankValue(hand.get(0).getRank());
        int highAllCard = Utility.getRankValue(allCards.get(0).getRank());
        for (int i = 0; i < hand.size(); i++){
            if (Utility.getRankValue(hand.get(i).getRank()) > highHandCard){
                highHandCard = Utility.getRankValue(hand.get(i).getRank());;
            }
        }
        for (int i = 0; i < allCards.size(); i++){
            if (Utility.getRankValue(allCards.get(i).getRank()) > highAllCard){
                highAllCard = Utility.getRankValue(allCards.get(i).getRank());;
            }
        }
        this.highAllCard = highAllCard;
        this.highHandCard = highHandCard;
        return highHandCard >= highAllCard;
    }



    public String playHand(ArrayList<Card> communityCards){
        // initializes the allcards variable with the community cards and the cards in the player's hand
        ArrayList<Card> allCards = new ArrayList<>();
        for (int i = 0; i < communityCards.size(); i++){
            allCards.add(communityCards.get(i));
        }
        for (int i = 0; i < hand.size(); i++){
            allCards.add(hand.get(i));
        }
        this.allCards = allCards;
        ArrayList<Integer> rankingFrequency = findRankingFrequency();
        // checkHighCard intializes highAllCard and highHandCard variables so they can always be used 
        checkHighCard();
        if (checkFlush() && checkStraight() && rankingFrequency.get(8) == 1 && rankingFrequency.get(12) == 1){
            return "Royal Flush";
        }else if (checkFlush() && checkStraight()){
            return "Straight Flush";
        }else if (checkFourOfAKind()){
            return "Four of a Kind";
        }else if (checkThreeOfAKind() && checkPair()){
            return "Full House";
        }else if (checkFlush()){
            return "Flush";
        }else if (checkStraight()){
            return "Straight";
        }else if (checkThreeOfAKind()){
            return "Three of a Kind";
        }else if (checkTwoPair()){
            return "Two Pair";
        }else if (checkPair()){
            return "A Pair";
        }else if (checkHighCard()){
            return "High Card";
        }
        return "Nothing";
    }

    public void sortAllCards(){
        for (int i = 0; i < hand.size(); i++){
            int idx = i;
            for (int j = i + 1; 0 < j && j < hand.size(); j--){
                if (Utility.getRankValue(hand.get(j).getRank()) < Utility.getRankValue(hand.get(idx).getRank())){
                    Card temp = hand.get(j);
                    hand.set(j, hand.get(idx));
                    hand.set(idx, temp);
                    idx--;
                }else {
                    break;
                }
            }
        }
        for (int i = 0; i < allCards.size(); i++){
            int idx = i;
            for (int j = i + 1; 0 < j && j < allCards.size(); j--){
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(idx).getRank())){
                    Card temp = allCards.get(j);
                    allCards.set(j, allCards.get(idx));
                    allCards.set(idx, temp);
                    idx--;
                }else {
                    break;
                }
            }
        }
    } 

    // finds how many of each value there for cards
    // uses getRankValue - 2 since it starts at 2 instead of 0
    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankingFrequencey = new ArrayList<>(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        for (int i = 0; i < rankingFrequencey.size(); i++){
            for (int j = 0; j < allCards.size(); j++){
                if (Utility.getRankValue(allCards.get(j).getRank()) - 2 == i){
                    rankingFrequencey.set(i, rankingFrequencey.get(i) + 1);
                }
            }
        }
        return rankingFrequencey; 
    }

    // uses the getSuitValue from Utility that was added to update the values in the list easier
    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<>(List.of(0, 0, 0, 0));
        for (int i = 0; i < allCards.size(); i++){
            suitFrequency.set(Utility.getSuitValue(allCards.get(i).getSuit()), suitFrequency.get(Utility.getSuitValue(allCards.get(i).getSuit())) + 1);
        }
        return suitFrequency; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
