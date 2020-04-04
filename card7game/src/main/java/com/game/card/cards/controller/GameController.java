package com.game.card.cards.controller;

import com.game.card.cards.model.Card;
import com.game.card.cards.model.Game;
import com.game.card.cards.model.Participant;
import com.game.card.cards.model.enums.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class GameController {


    @Autowired
    AppSession sessionVariable;

    @Autowired
    ShuffleCardController shuffleCardController;

    @RequestMapping(method = RequestMethod.POST, path = "/joinRool")
    public Participant joinRoom(String roomCode, String participantPin, String participantName){

        if(sessionVariable.getGameRooms().containsKey(Integer.parseInt(roomCode))){
            return addParticipantToGame(roomCode, participantPin, participantName, false);
        }else {
            System.out.print("Room not found.....");
            return null;
        }
    }

    public Participant addParticipantToGame(String roomIdStr, String participantIdStr, String participantName, boolean adminParticipant){
        int participantId = Integer.parseInt(participantIdStr);
        int roomId = Integer.parseInt(roomIdStr);
        if(sessionVariable.getGameRooms().get(roomId).getGame().getParticipantMap().keySet().contains(participantId)){
            System.out.println("Participant Already Exists.....");
            return null;
        }
        if (sessionVariable.getGameRooms().get(roomId).getGame().getParticipantMap().keySet().size() > 5 ){
            System.out.println("Room is full....");
            return null;
        }

        Participant participant = new Participant();
        participant.setParticipantName(participantName);
        participant.setParticipantId(participantId);
        participant.setAdminParticipant(adminParticipant);
        sessionVariable.getGameRooms().get(roomId).getGame().getParticipantMap().put(participantId, participant);
        return participant;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/startGame")
    public Map<Integer, Participant> startGame(String roomIdStr, String participantPin){
        int participantId = Integer.parseInt(participantPin);

        int roomId = Integer.parseInt(roomIdStr);
        Game game = sessionVariable.getGameRooms().get(roomId).getGame();
        if (game.isGameStarted()){
            System.out.println("Game Already Started.....");
            return null;
        }
        if(game.getAdminParticipantId() != participantId){
            System.out.println("Request " + game.getParticipantMap().get(game.getAdminParticipantId()).getParticipantName() + " to start the game... ");
            return  null;
        }
        List<List<Card>> cardList = shuffleCardController.allocate(game.getParticipantMap().keySet().size() + "");
        int participantSequence = 2;
        Set<Integer> partKey = game.getParticipantMap().keySet();
        Map<Integer, Participant> participantMap = game.getParticipantMap();
        int i = 0;
        boolean setFirstPlayer = false;
        for (int partId: partKey){
            setFirstPlayer = false;
            Participant participant = participantMap.get(partId);

            List<Card> cards  = cardList.get(i);
            for(Card card : cards){
                if(card.getCardType().equals(CardType.HEART) && card.getCardPoint() == 7){
                    setFirstPlayer = true;
                }
                Map<CardType, List<Card>> availableCardMap = participant.getAvailableCardMap();
                if(availableCardMap.get(card.getCardType()) == null){
                    availableCardMap.put(card.getCardType(), new ArrayList<Card>());
                }
            }
            i++;
            if(setFirstPlayer){
                participant.setParticipantSequenceToPlay(1);
            }else {
                participant.setParticipantSequenceToPlay(participantSequence);
                participantSequence++;
            }
        }
        game.setGameStarted(true);

        return game.getParticipantMap();
    }
}
