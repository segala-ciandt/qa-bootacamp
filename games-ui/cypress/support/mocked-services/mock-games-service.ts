export class MockGamesServiceAlias {
    static getGamesAlias: string = "getGamesAlias";
    static playGamesFromTop: string = "playGamesFromTop";
}

export class MockGamesService {
    public mockGetGamesHistory():void {
        cy.intercept('GET', 'games?*', {
            statusCode: 200,
            fixture: 'get-games-history.json'
          }).as(MockGamesServiceAlias.getGamesAlias);      
    }

    public mockPlayGamesFromTop():void {
        cy.intercept('GET', '/play', {
            statusCode: 200,
            fixture: 'play-games.json'
          }).as(MockGamesServiceAlias.playGamesFromTop);      
    }
}