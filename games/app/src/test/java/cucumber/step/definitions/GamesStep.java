package cucumber.step.definitions;

import com.ciandt.games.Game;
import com.ciandt.games.playingcard.CardSuit;
import com.ciandt.games.playingcard.PlayingCard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.config.SpringIntegrationTest;
import cucumber.config.utils.RestUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;


@AllArgsConstructor
public class GamesStep extends SpringIntegrationTest {

    private RestUtils restUtils;

    @When("^I play from the top$")
    public void iPlayFromTheTop() {
        ResponseEntity<String> playResponse = restUtils.get("/play");
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

    @And("^game cards are saved$")
    public void gameCardsAreSaved() throws JsonProcessingException {
        ResponseEntity<String> response = testContext.getResponse();
        Game gameResponse = objectMapper.readValue(response.getBody(), Game.class);

        gameRepository.findAll().stream().anyMatch(gameCard -> gameCard.equals(gameResponse.getCard1()));
        gameRepository.findAll().stream().anyMatch(gameCard -> gameCard.equals(gameResponse.getCard2()));
    }
}
