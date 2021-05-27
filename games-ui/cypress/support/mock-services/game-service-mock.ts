export class GameServiceMock {
    public mockGetPlayResponse(): void {
        cy.intercept("GET", "/play", {
            statusCode: 200,
            fixture: "get-play.json"
        });
    }

    public mockGetGamesResponse(): void {
        cy.intercept("GET", "/games?*", {
            statusCode: 200,
            fixture: "get-games.json"
        });
    }
}