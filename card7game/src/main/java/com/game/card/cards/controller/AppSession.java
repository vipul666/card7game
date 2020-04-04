package com.game.card.cards.controller;

import com.game.card.cards.model.GameRoom;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AppSession {

    public HashMap<Integer, GameRoom> gameRooms = new HashMap<>();

    public HashMap<Integer, GameRoom> getGameRooms() {
        return gameRooms;
    }

    public void setGameRooms(HashMap<Integer, GameRoom> gameRooms) {
        this.gameRooms = gameRooms;
    }
}
