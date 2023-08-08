package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDealingStrategies {
    static class CardClass implements Card {
        String name;
        CardClass(String name) {
            this.name = name;
        }

        @Override
        public String name() {
            return name;
        }

        public String toString() {
            return name;
        }
    }
    static class CardDealingStrategyClass implements CardDealingStrategy {
        int cardCount;
        int deckCardCount;
        int trumpCardCount;
        CardDealingStrategyClass(int cardCount, int deckCardCount, int trumpCardCount) {
            this.cardCount = cardCount;
            this.deckCardCount = deckCardCount;
            this.trumpCardCount = trumpCardCount;
        }

        @Override
        public Map<String, List<Card>> dealStacks(Deck deck, int players) {
            List<String> keyList = new ArrayList<>();
            List<List<Card>> valueList = new ArrayList<>();


            for (int i = 0; i < cardCount; i++) {
                for (int j = 0; j < players; j++) {
                    if (keyList.size() < players) {
                        keyList.add("Player " + (j + 1));
                        valueList.add(new ArrayList<Card>());
                    }
                    int finalI = i;
                    int finalJ = j;
                    List<Card> cards = valueList.get(j);
                    cards.add(deck.dealCard());
                    valueList.set(j, cards);
                }
            }
            List<Card> cardList = new ArrayList<>();
            Map<String, List<Card>> map = new HashMap<>();
            int lastValue = (deck.size() - cardCount * players) - 1;
            for (int i = lastValue; i > lastValue - deckCardCount; i--) {
                cardList.add(deck.dealCard());
            }

            if (cardList.size() != 0) {
                map.put("Community", cardList);
            }
            for (int i = 0; i < players; i++) {
                map.put(keyList.get(i), valueList.get(i));
            }

            List<Card> trumpCardList = new ArrayList<>();
            for (int i = lastValue; i > lastValue - trumpCardCount; i--) {
                trumpCardList.add(deck.dealCard());
            }
            cardList = new ArrayList<>();
            while (deck.size() != 0) {
                cardList.add(deck.dealCard());
            }
            if (cardList.size() != 0) {
                map.put("Remaining", cardList);
            }
            if (trumpCardList.size() != 0) {
                map.put("Trump card", trumpCardList);
            }
            return map;
        }
    }
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new CardDealingStrategyClass(2,5, 0);
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new CardDealingStrategyClass(5,0, 0);
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return new CardDealingStrategyClass(13,0, 0);
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return new CardDealingStrategyClass(6,0, 1);
    }

}
