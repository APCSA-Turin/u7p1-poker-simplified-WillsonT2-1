package com.example.project;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        boolean continuePlaying = true;
        int player1Wins = 0;
        int player2Wins = 0;
        while (continuePlaying){
            Deck deck1 = new Deck();
            deck1.initializeDeck();
            deck1.shuffleDeck();
            ArrayList<Card> communityCards = new ArrayList<>();
            Player player1 = new Player();
            Player player2 = new Player();
            communityCards.add(deck1.drawCard());
            communityCards.add(deck1.drawCard());
            communityCards.add(deck1.drawCard());
            player1.addCard(deck1.drawCard());
            player1.addCard(deck1.drawCard());
            player2.addCard(deck1.drawCard());
            player2.addCard(deck1.drawCard());
            System.out.println("Community Cards: ");
            for (int i = 0; i < communityCards.size(); i++){
                System.out.println(communityCards.get(i).toString());
            }
            System.out.println();
            System.out.println("Player 1 Cards / Your Cards: ");
            for (int i = 0; i < player1.getHand().size(); i++){
                System.out.println(player1.getHand().get(i).toString());
            }
            System.out.println();
            System.out.println("Player 2 Cards: ");
            for (int i = 0; i < player2.getHand().size(); i++){
                System.out.println(player2.getHand().get(i).toString());
            }
            System.out.println();
            String result = Game.determineWinner(player1, player2, player1.playHand(communityCards), player2.playHand(communityCards), communityCards);
            if (result.equals("Player 1 wins!")){
                System.out.println("Player 1 has " + player1.playHand(communityCards) + "!");
                player1Wins++;
            }else if (result.equals("Player 2 wins!")){
                System.out.println("Player 2 has " + player2.playHand(communityCards) + "!");
                player2Wins++;
            }else {
                System.out.println("Both have " + player1.playHand(communityCards) + "!");
            }
            System.out.println(result);
            System.out.println("Your wins: " + player1Wins + "\nPlayer 2 Wins: " + player2Wins);
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.println("Continue Playing? (Y/N)");
            String answer = scan.nextLine();
            if (answer.equals("N")){
                continuePlaying = false;
            }
            System.out.println("--------------------------------------");
        }
    }
}