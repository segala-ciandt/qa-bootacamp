import { Given, Then, When } from 'cypress-cucumber-preprocessor/steps';
import { MockGamesService } from '../mocked-services/mock-games-service';
import { MockTime } from '../mocked-services/mock-time';
import { GamePageObjects } from '../page-objects/game-po';

const gamePageObjects: GamePageObjects = new GamePageObjects();
const mockGamesService: MockGamesService = new MockGamesService();
const mockTime: MockTime = new MockTime();

Given("now is {string}", (now: string) => {
    mockTime.mockNow(now);
});

Given("I am on game app", () => {
    gamePageObjects.visitGameApp();
});

Given("game services is mocked", () => {
    mockGamesService.mockGetGamesHistory();
    mockGamesService.mockPlayGamesFromTop();
});

When("I play a game", () => {
    gamePageObjects.playAGame();
});

Then("I get two top cards", () => {
    let card1: string = "A ♦";
    let card2: string = "A ♥";    
    gamePageObjects.shouldSeeTwoCards(card1, card2);
});