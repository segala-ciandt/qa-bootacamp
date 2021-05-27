import { Given, Then, When } from "cypress-cucumber-preprocessor/steps";
import { GamePageObjects } from "../page-objects/game-page-objects";

const gamePageObjects: GamePageObjects = new GamePageObjects();

Given("that I open the game page", () => {
    gamePageObjects.visitGameUrl();
});

When("I click on the {string} button", (buttonText: string) => {
    gamePageObjects.clickButton(buttonText);
});

Then("I can see the text {string}", (expectedDisplayedText: string) => {
    gamePageObjects.verifyTextIsDisplayed(expectedDisplayedText);
});

Then("I can see these elements on the screen", (dataTable) => {
    dataTable.hashes().forEach((row: { elements: string; }) => {
        gamePageObjects.verifyTextIsDisplayed(row.elements)
    });
});

Then("the game displays the two played cards", () => {
    gamePageObjects.verifyCardOneIsDisplayedWithValue(" A ♥ ");
    gamePageObjects.verifyCardTwoIsDisplayedWithValue(" A ♦ ");    
});

Then("history should be updated with cards game", () => {
    gamePageObjects.verifyCardOneIsDisplayedInHistoryTable(" A ♥ ");
    gamePageObjects.verifyCardTwoIsDisplayedInHistoryTable(" A ♦ ");
})