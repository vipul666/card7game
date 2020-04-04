package com.game.card.cards.model;

import com.game.card.cards.model.enums.CardType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participant {

    private int participantId;
    private int participantSequenceToPlay;
    private String participantName;
    private Map<CardType, List<Card>> availableCardMap = new HashMap<>();
    private boolean adminParticipant;
    private int score;

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getParticipantSequenceToPlay() {
        return participantSequenceToPlay;
    }

    public void setParticipantSequenceToPlay(int participantSequenceToPlay) {
        this.participantSequenceToPlay = participantSequenceToPlay;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public Map<CardType, List<Card>> getAvailableCardMap() {
        return availableCardMap;
    }

    public void setAvailableCardMap(Map<CardType, List<Card>> availableCardMap) {
        this.availableCardMap = availableCardMap;
    }

    public boolean isAdminParticipant() {
        return adminParticipant;
    }

    public void setAdminParticipant(boolean adminParticipant) {
        this.adminParticipant = adminParticipant;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
