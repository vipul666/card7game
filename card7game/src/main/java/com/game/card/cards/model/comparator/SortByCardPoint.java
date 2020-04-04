package com.game.card.cards.model.comparator;

import com.game.card.cards.model.Card;

import java.util.Comparator;

public class SortByCardPoint implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.getCardPoint() - o2.getCardPoint();
    }
}
