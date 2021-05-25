package com.ciandt.games;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import com.ciandt.games.dealer.DealerService;
import com.ciandt.games.playingcard.PlayingCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.val;

@Service
public class GameService {
    @Autowired
    private DealerService dealerService;
    @Autowired
    private GameRepository repository;

    public List<Game> findAll() {
        return repository.findAll();
    }

    public Game play() {
        val playedDeck = dealerService.getShuffled();
        return repository.save(play(playedDeck));
    }

    public Game playFromTheBottom() {
        val playedDeck = dealerService.getShuffled();
        return repository.save(play(playedDeck));
    }

    private Game play(List<PlayingCard> playedDeck) {
        if (playedDeck.size() < 2) {
            throw new RuntimeException("You cannot play a game with less than 2 cards");
        }

        val card1 = playedDeck.get(0);
        val card2 = playedDeck.get(1);

        return Game.builder().card1(card1).card2(card2).timestamp(Instant.now()).playedDeck(playedDeck).build();
    }
}
