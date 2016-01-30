package com.cm.manage.util;

import com.cm.manage.util.json.JsonBinder;

import java.io.Serializable;

/**
 * Created by yuanxiaozheng on 15/1/19.
 */
/*
   A subclass of the Hand class that represents a hand of cards
   in the game of Blackjack.  To the methods inherited form Hand,
   it adds the method getBlackjackHand(), which returns the value
   of the hand for the game of Blackjack.
*/
public class BlackjackHand extends Hand implements Serializable {
    public int getBlackjackValue() {
        // Returns the value of this hand for the
        // game of Blackjack.

        int val;      // The value computed for the hand.
        boolean ace;  // This will be set to true if the
        //   hand contains an ace.
        int cards;    // Number of cards in the hand.

        val = 0;
        ace = false;
        cards = clacCardCount();

        for ( int i = 0;  i < cards;  i++ ) {
            // Add the value of the i-th card in the hand.
            Poker card;    // The i-th card;
            int cardVal;  // The blackjack value of the i-th card.
            card = getCard(i);
            cardVal = card.getValue();  // The normal value, 1 to 13.
            if (cardVal > 10) {
                cardVal = 10;   // For a Jack, Queen, or King.
            }
            if (cardVal == 1) {
                ace = true;     // There is at least one ace.
            }
            val = val + cardVal;
        }

        // Now, val is the value of the hand, counting any ace as 1.
        // If there is an ace, and if changing its value from 1 to
        // 11 would leave the score less than or equal to 21,
        // then do so by adding the extra 10 points to val.

        if ( ace == true  &&  val + 10 <= 21 )
            val = val + 10;

        return val;

    }  // end getBlackjackValue()

    public String toJSON(){
        return JsonBinder.buildNonNullBinder().toJson(this);
    }

    public static BlackjackHand fromJSON(String jsonStr){
        return JsonBinder.buildNonNullBinder().fromJson(jsonStr, BlackjackHand.class);
    }

    @Override
    public String toString() {
        return "BlackjackHand{} " + super.toString();
    }

    public static void main(String[] args) throws Exception{
//        BlackjackHand testDeck = new BlackjackHand();
//        String jsonStr = SerializeUtil.writeObject(testDeck);
//        System.out.println("init pokerContent: " + jsonStr.length() + ",data: " + jsonStr);
//        System.out.println("origDeck toString(): " + testDeck);
//        Poker poker0 = new Poker(1,1);
//        Hand hand0 = new Hand();
//        hand0.addCard(poker0);
//        System.out.println(poker0.toJSON());
//        System.out.println(hand0.toJSON());

        String jsonStr = "{\"hand\":[{\"value\":2,\"suit\":0},{\"value\":13,\"suit\":0}]}";
        BlackjackHand theHand = BlackjackHand.fromJSON(jsonStr);
        System.out.println("pokeHand toString(): " + theHand);
    }
}
