package cucumber.step.hooks;

import com.ciandt.dealer.playingcard.CardSuit;
import com.ciandt.dealer.playingcard.PlayingCard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.api.java.Before;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class Hooks {
    @Before
    public static void setUp() throws JsonProcessingException {
        PlayingCard playingCard1 = new PlayingCard(1, CardSuit.CLUBS);
        PlayingCard playingCard2 = new PlayingCard(2, CardSuit.HEARTS);
        PlayingCard playingCard3 = new PlayingCard(3, CardSuit.SPADES);
        PlayingCard playingCard4 = new PlayingCard(4, CardSuit.DIAMONDS);

        List<PlayingCard> notShuffledDeck = Arrays.asList(playingCard1, playingCard2, playingCard3, playingCard4);

        ObjectMapper objectMapper = new ObjectMapper();
        String notShuffledStringDeck = objectMapper.writeValueAsString(notShuffledDeck);

        WireMock
                .stubFor(WireMock.get(WireMock.urlMatching("/"))
                .willReturn(aResponse()
                .withHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)
                .withStatus(200)
                .withBody(notShuffledStringDeck)));
    }
}
