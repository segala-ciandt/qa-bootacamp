Feature: Game

    Scenario: Play a game
        Given now is "2021-04-20T10:00:00"
        And game services is mocked
        And I am on game app
        When I play a game
        Then I get two top cards
        