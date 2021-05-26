import moment = require("moment");

export class MockTime{
    public mockNow(dateTime: string): void {
        cy.clock(moment(dateTime).toDate());
    }
}