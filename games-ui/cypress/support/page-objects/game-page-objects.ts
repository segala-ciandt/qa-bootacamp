export class GameElements {
    static cardLocator: string = ".mat-card"
    static historyTableCell: string = ".mat-cell"
    static cardOneLocator: string = ":nth-child(1) > app-playing-card > .mat-card > div"
}

export class GamePageObjects {
    public visitGameUrl(): void {
        cy.visit("localhost:4200");
    }

    public verifyTextIsDisplayed(text: string): void {
        cy.contains(text).should("be.visible");
    }

    public clickButton(buttonText: string): void {
        cy.contains(buttonText).click();
    }

    public verifyCardOneIsDisplayedWithValue(cardValue: string): void {
        cy.get(GameElements.cardOneLocator)
            .should("have.text", cardValue);
    }

    public verifyCardTwoIsDisplayedWithValue(cardValue: string): void {
        cy.get(GameElements.cardLocator)
            .eq(1)
            .should("have.text", cardValue);
    }

    public verifyCardOneIsDisplayedInHistoryTable(cardValue: string):void {        
        cy.get(GameElements.historyTableCell)
            .find(".mat-card")
            .eq(0)
            .should("have.text", cardValue);
    }

    public verifyCardTwoIsDisplayedInHistoryTable(cardValue: string):void {
        cy.get(GameElements.historyTableCell)
            .find(".mat-card")
            .eq(1)
            .should("have.text", cardValue);
    }
}