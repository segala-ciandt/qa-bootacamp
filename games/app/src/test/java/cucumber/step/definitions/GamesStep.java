package cucumber.step.definitions;

import com.ciandt.games.Game;
import com.ciandt.games.playingcard.CardSuit;
import com.ciandt.games.playingcard.PlayingCard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.config.SpringIntegrationTest;
import cucumber.config.utils.RestUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@AllArgsConstructor
public class GamesStep extends SpringIntegrationTest {

    private RestUtils restUtils;

    @Given("^I have played games$")
    public void iHavePlayedGames() {
        Instant timestamp = Instant.now();
        PlayingCard playedCard1 = new PlayingCard(1, CardSuit.CLUBS);
        PlayingCard playedCard2 = new PlayingCard(2, CardSuit.HEARTS);
        List<PlayingCard> playedDeck = Arrays.asList(playedCard1, playedCard2);

        Game game1 = new Game(1L,timestamp,playedCard1,playedCard2,playedDeck);
        Game game2 = new Game(2L,timestamp,playedCard1,playedCard2,playedDeck);
        gameRepository.save(game1);
        gameRepository.save(game2);
    }

    @When("^I play from the top$")
    public void iPlayFromTheTop() {
        ResponseEntity<String> playResponse = restUtils.get("/play");
        testContext.setResponse(playResponse);
    }

    @When("^I ask for all the games$")
    public void iAskForAllTheGames() {
        ResponseEntity<String> playResponse = restUtils.get("/games");
        testContext.setResponse(playResponse);
    }

    @Then("^I get 2 cards from the top from a shuffled deck$")
    public void iGetTwoCardsFromTheTopFromAShuffledDeck() throws JsonProcessingException {
        PlayingCard topCard1 = new PlayingCard(1, CardSuit.CLUBS);
        PlayingCard topCard2 = new PlayingCard(2, CardSuit.HEARTS);

        WireMock.verify(1, WireMock.getRequestedFor(WireMock.urlMatching("/shuffled")));
        ResponseEntity<String> response = testContext.getResponse();

        Game gameResponse = objectMapper.readValue(response.getBody(), Game.class);
        assertEquals(topCard1, gameResponse.getCard1());
        assertEquals(topCard2, gameResponse.getCard2());
    }

    @Then("^I get a list of all played games$")
    public void iGetAListOfALlPlayedGames() throws JsonProcessingException {
        ResponseEntity<String> response = testContext.getResponse();
        List<Game> gameList = objectMapper.readValue(response.getBody(), new TypeReference<List<Game>>() {});
        assertEquals(2, gameList.size());
    }

    @And("^game cards are saved$")
    public void gameCardsAreSaved() throws JsonProcessingException {
        ResponseEntity<String> response = testContext.getResponse();
        Game gameResponse = objectMapper.readValue(response.getBody(), Game.class);

        gameRepository.findAll().stream().allMatch(gameCard -> gameCard.getId().equals(gameResponse.getId()));
        gameRepository.findAll().stream().anyMatch(gameCard -> gameCard.equals(gameResponse.getCard1()));
        gameRepository.findAll().stream().anyMatch(gameCard -> gameCard.equals(gameResponse.getCard2()));
    }


}
