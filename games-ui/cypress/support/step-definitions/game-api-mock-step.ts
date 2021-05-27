import { Given } from "cypress-cucumber-preprocessor/steps";
import { GameServiceMock } from "../mock-services/game-service-mock";

const gameServicesMock: GameServiceMock = new GameServiceMock();

Given("I have setup games microservice", () => {
    gameServicesMock.mockGetPlayResponse();
    gameServicesMock.mockGetGamesResponse();
});