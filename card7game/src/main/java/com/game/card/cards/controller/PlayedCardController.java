package com.game.card.cards.controller;

import com.game.card.cards.model.Card;
import com.game.card.cards.model.enums.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PlayedCardController {

    @Autowired
    AppSession appSession;


    @RequestMapping(path = "/getAllPlayedCards", method = RequestMethod.GET)
    public Map<CardType, List<Card>> getAllPlayedCardListsOfGame(String gameRoomId){

        return appSession.getGameRooms().get(Integer.parseInt(gameRoomId)).getGame().getPlayedCardMap();

    }
}
