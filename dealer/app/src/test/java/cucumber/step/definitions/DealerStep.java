package cucumber.step.definitions;

import com.ciandt.dealer.playingcard.CardSuit;
import com.ciandt.dealer.playingcard.PlayingCard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.config.SpringIntegrationTest;
import cucumber.config.utils.RestUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
public class DealerStep extends SpringIntegrationTest {

    private RestUtils restUtils;

    @When("^Game requests a shuffled deck$")
    public void gameRequestsAShuffledDeck() {
        testContext.setResponse(restUtils.get("/shuffled"));
    }

    @Then("^Game gets a shuffled deck$")
    public void gameGetsAShuffledDeck() throws JsonProcessingException {
        ResponseEntity<String> response = testContext.getResponse();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        WireMock.verify(WireMock.getRequestedFor(WireMock.urlMatching("/")));

        List<PlayingCard> responseDeck = objectMapper.readValue(response.getBody(),
                new TypeReference<List<PlayingCard>>() { });

        PlayingCard playingCard1 = new PlayingCard(1, CardSuit.CLUBS);
        PlayingCard playingCard2 = new PlayingCard(2, CardSuit.HEARTS);
        PlayingCard playingCard3 = new PlayingCard(3, CardSuit.SPADES);
        PlayingCard playingCard4 = new PlayingCard(4, CardSuit.DIAMONDS);

        List<PlayingCard> notShuffledDeck = Arrays.asList(playingCard1, playingCard2, playingCard3, playingCard4);

        assertNotEquals(notShuffledDeck, responseDeck);
    }
}
