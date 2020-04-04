package com.game.card.cards.model;

import com.game.card.cards.model.enums.CardType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private int gameId;
    private int adminParticipantId;
    private boolean gameStarted = false;
    private Map<Integer, Participant> participantMap = new HashMap<>();
    private Map<CardType, List<Card>> playedCardMap = new HashMap<>();
    private  int participantSequenceToPlay = 1;
    private boolean isHeartWith_7_Played = false;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getAdminParticipantId() {
        return adminParticipantId;
    }

    public void setAdminParticipantId(int adminParticipantId) {
        this.adminParticipantId = adminParticipantId;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public Map<Integer, Participant> getParticipantMap() {
        return participantMap;
    }

    public void setParticipantMap(Map<Integer, Participant> participantMap) {
        this.participantMap = participantMap;
    }

    public Map<CardType, List<Card>> getPlayedCardMap() {
        return playedCardMap;
    }

    public void setPlayedCardMap(Map<CardType, List<Card>> playedCardMap) {
        this.playedCardMap = playedCardMap;
    }

    public int getParticipantSequenceToPlay() {
        return participantSequenceToPlay;
    }

    public void setParticipantSequenceToPlay(int participantSequenceToPlay) {
        this.participantSequenceToPlay = participantSequenceToPlay;
    }

    public boolean isHeartWith_7_Played() {
        return isHeartWith_7_Played;
    }

    public void setHeartWith_7_Played(boolean heartWith_7_Played) {
        isHeartWith_7_Played = heartWith_7_Played;
    }
}
