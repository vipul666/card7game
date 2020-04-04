package com.game.card.cards.controller;

import com.game.card.cards.model.Card;
import com.game.card.cards.model.Game;
import com.game.card.cards.model.Participant;
import com.game.card.cards.model.PlayCard;
import com.game.card.cards.model.comparator.SortByCardPoint;
import com.game.card.cards.model.enums.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SubmitCardController {

    @Autowired
    AppSession appSession;


    public void submitCard(@RequestBody PlayCard playedDetails){

        int roomId = Integer.parseInt(playedDetails.getRoomId());
        Game sessionGame = appSession.getGameRooms().get(roomId).getGame();

        boolean gameInitialized = sessionGame.isHeartWith_7_Played();
        Card playedCard = playedDetails.getCard();
        if(!gameInitialized && playedCard.getCardType() == CardType.HEART && playedCard.getCardPoint() == 7){

            Map<CardType, List<Card>> playedCardMap = new HashMap<>();
            List<Card> heartCardList = new ArrayList<>();
            List<Card> spadesCardList = new ArrayList<>();
            List<Card> diamondCardList = new ArrayList<>();
            List<Card> clubCardList = new ArrayList<>();
            heartCardList.add(playedCard);
            playedCardMap.put(CardType.HEART, heartCardList);
            playedCardMap.put(CardType.SPADES, spadesCardList);
            playedCardMap.put(CardType.CLUBS, clubCardList);
            playedCardMap.put(CardType.DIAMONDS, diamondCardList);

            sessionGame.setPlayedCardMap(playedCardMap);
            sessionGame.setHeartWith_7_Played(true);
        }else if (gameInitialized){

            Participant sessionParticipant = sessionGame.getParticipantMap().get(playedDetails.getParticipantId());
            if(sessionParticipant.getParticipantSequenceToPlay() == sessionGame.getParticipantSequenceToPlay()){
                int playedCardPoint = playedCard.getCardPoint();
                CardType playedCardType = playedCard.getCardType();

                List<Card> sessionPlayedCardListPlayedCardType = sessionGame.getPlayedCardMap().get(playedCardType);
                Collections.sort(sessionPlayedCardListPlayedCardType, new SortByCardPoint());

                if(sessionPlayedCardListPlayedCardType.get(0).getCardPoint() -1 == playedCardPoint ||
                        sessionPlayedCardListPlayedCardType.get(sessionPlayedCardListPlayedCardType.size() - 1).getCardPoint()+1 == playedCardPoint ||
                        sessionPlayedCardListPlayedCardType.get(0).getCardPoint() == 7 ){

                    boolean cardRemoved = sessionParticipant.getAvailableCardMap().get(playedCard.getCardType()).remove(playedCard);

                    if(cardRemoved){

                        sessionGame.getPlayedCardMap().get(playedCard.getCardType()).add(playedCard);
                    }
                    int nextPlayingParticipant = sessionParticipant.getParticipantSequenceToPlay() + 1;
                    if(nextPlayingParticipant == sessionGame.getParticipantMap().keySet().size()){
                        nextPlayingParticipant = 1;
                    }
                    sessionGame.setParticipantSequenceToPlay(nextPlayingParticipant);

                }
            }


        }else{
            System.out.println("Play Heart with 7 first");
        }
    }


}
