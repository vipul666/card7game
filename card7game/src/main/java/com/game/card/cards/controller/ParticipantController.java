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
public class ParticipantController {

    @Autowired
    AppSession appSession;

    @RequestMapping(method = RequestMethod.GET, path = "/getAvailableCards")
    public Map<CardType, List<Card>> getAvailableCards(String gameRoomId, String participantId) {
        return appSession.getGameRooms().get(Integer.parseInt(gameRoomId))
                .getGame().getParticipantMap().get(Integer.parseInt(participantId)).getAvailableCardMap();
    }
}
