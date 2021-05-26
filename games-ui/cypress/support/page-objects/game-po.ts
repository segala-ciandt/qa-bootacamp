import { GameElements } from "../elements/game-elements";
import { MockGamesServiceAlias } from "../mocked-services/mock-games-service";

export class GamePageObjects {
    public visitGameApp(): void {
        cy.visit("http://localhost:4200")
    }

    public playAGame(): void {
        cy.contains(GameElements.playFromTopText).click();
    }

    public shouldSeeTwoCards(card1: string, card2: string): void {
        cy.contains(GameElements.matCard, card1).should('be.visible');
        cy.contains(GameElements.matCard, card2).should('be.visible');
    }
}