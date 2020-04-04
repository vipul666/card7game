package com.game.card.cards.model;

import com.game.card.cards.model.enums.CardLevel;
import com.game.card.cards.model.enums.CardType;

public class Card {

    private  CardType cardType;
    private CardLevel cardLevel;
    private int cardPoint;

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardLevel getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(CardLevel cardLevel) {
        this.cardLevel = cardLevel;
    }

    public int getCardPoint() {
        return cardPoint;
    }

    public void setCardPoint(int cardPoint) {
        this.cardPoint = cardPoint;
    }
}
