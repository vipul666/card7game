package com.game.card.cards.controller;

import com.game.card.cards.model.Card;
import com.game.card.cards.model.enums.CardLevel;
import com.game.card.cards.model.enums.CardType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

@RestController
public class ShuffleCardController {

    @RequestMapping(method = RequestMethod.GET, path = "/allocateCard")
    public List<List<Card>> allocate(String participants){

        int participantsCount = Integer.parseInt(participants);


        List<List<Card>> participantCardsList = createParticipantList(participantsCount);

        List<Card> cards = accumulateCards();

        allocateCardToParticipant(participantsCount, participantCardsList, cards);

        return participantCardsList;




    }

    private void allocateCardToParticipant(int participantsCount, List<List<Card>> participantCardsList, List<Card> cards){

        int remainingListSize = cards.size();

        while (remainingListSize > 0){
            for(int i=0; i < participantsCount; i++){
                if(remainingListSize == 0 ){
                    break;
                }
                Random random = new Random();
                int index;

                if(remainingListSize > 2 ){
                    index = random.nextInt(remainingListSize - 1 );
                }else if (remainingListSize == 2 ){
                    index =1 ;
                }else {
                    index = 0;
                }
                Card cardToAssign = cards.get(index);
                cards.remove(index);
                List<Card> partCardList = participantCardsList.get(i);
                partCardList.add(cardToAssign);
                remainingListSize--;
            }
        }
    }

    private List<Card> accumulateCards() {
        List<Card> cards = new ArrayList<>();
        EnumSet<CardType> cardTypes = EnumSet.allOf(CardType.class);

        for(CardType cardType : cardTypes){
            EnumSet<CardLevel> cardLevels = EnumSet.allOf(CardLevel.class);
            for(CardLevel cardLevel : cardLevels){

                if(cardLevel.equals(CardLevel.OTHERS)){
                    for(int i=2; i <= 10; i++){
                        Card card = new Card();
                        card.setCardLevel(cardLevel);
                        card.setCardPoint(i);
                        card.setCardType(cardType);
                        cards.add(card);
                    }
                }else if (cardLevel.equals(CardLevel.ACE)){
                    Card card = new Card();
                    card.setCardLevel(cardLevel);
                    card.setCardPoint(1);
                    card.setCardType(cardType);
                    cards.add(card);

                }else if (cardLevel.equals(CardLevel.JACK)){
                    Card card = new Card();
                    card.setCardLevel(cardLevel);
                    card.setCardPoint(1);
                    card.setCardType(cardType);
                    cards.add(card);

                }else if (cardLevel.equals(CardLevel.QUEEN)){
                    Card card = new Card();
                    card.setCardLevel(cardLevel);
                    card.setCardPoint(1);
                    card.setCardType(cardType);
                    cards.add(card);

                }else if (cardLevel.equals(CardLevel.KING)){
                    Card card = new Card();
                    card.setCardLevel(cardLevel);
                    card.setCardPoint(1);
                    card.setCardType(cardType);
                    cards.add(card);

                }
            }
        }
        return cards;

    }

    private List<List<Card>> createParticipantList(int participantsCount) {

        List<List<Card>> participantsCardsList =  new ArrayList<>();
        for(int i=0; i < participantsCount ; i++){
            List<Card> participantList = new ArrayList<>();
            participantsCardsList.add(participantList);
        }

        return participantsCardsList;

    }
}
