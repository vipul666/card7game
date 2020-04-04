package com.game.card.cards.controller;

import com.game.card.cards.model.Game;
import com.game.card.cards.model.GameRoom;
import com.game.card.cards.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
public class GameRoomController {

    @Autowired
    AppSession session;

    @Autowired
    GameController gameController;

    @RequestMapping(method = RequestMethod.POST, path = "/createRoom")
    public String createRoom(@RequestBody Participant participant) {

        int participantId = participant.getParticipantId();
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);

        String roomId = String.format("%05d", num);

        GameRoom gameRoom = new GameRoom();
        Game game = new Game();
        game.setGameId((int) System.currentTimeMillis());
        game.setAdminParticipantId(participantId);
        gameRoom.setGame(game);

        session.getGameRooms().put(Integer.parseInt(roomId), gameRoom);
        gameController.addParticipantToGame(roomId, participant.getParticipantId() + "", participant.getParticipantName(), true
        );
            return roomId;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/deleteRoom")
    public  void stopGame(String roomId){
        session.getGameRooms().remove(Integer.parseInt(roomId));
    }

}
