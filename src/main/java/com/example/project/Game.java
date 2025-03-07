package com.example.project;
import java.util.ArrayList;


public class Game{

    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        p1Hand = p1.playHand(communityCards);
        p2Hand = p2.playHand(communityCards);
        if (Utility.getHandRanking(p2Hand) > Utility.getHandRanking(p1Hand)){
            return "Player 2 wins!";
        }else if (Utility.getHandRanking(p2Hand) < Utility.getHandRanking(p1Hand)){
            return "Player 1 wins!";
        }
        return tieBreaker(p1, p2);
    }

    // used to check and determine winner when two players have the same hand type 
    public static String tieBreaker(Player p1, Player p2){
        int p1Num = Utility.getRankValue(p1.getAllCards().get(0).getRank());
        int p2Num = Utility.getRankValue(p2.getAllCards().get(0).getRank());
        for (int i = 0; i < p1.getAllCards().size(); i++){
            if (Utility.getRankValue(p1.getAllCards().get(i).getRank()) > p1Num){
                p1Num = Utility.getRankValue(p1.getAllCards().get(i).getRank());
            }
        }
        for (int i = 0; i < p2.getAllCards().size(); i++){
            if (Utility.getRankValue(p2.getAllCards().get(i).getRank()) > p2Num){
                p2Num = Utility.getRankValue(p2.getAllCards().get(i).getRank());
            }
        }
        // updates the variables to highCards in hand if they both have same highCard due to community cards
        if (p2Num == p1Num){
            p2Num = p2.getHighHandCard();
            p1Num = p1.getHighHandCard();   
        }

        if (p1Num > p2Num){
            return "Player 1 wins!";
        }else if (p2Num > p1Num) {
            return "Player 2 wins!";
        }
        return "Tie!";
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}