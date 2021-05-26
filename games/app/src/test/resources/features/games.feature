Feature: Games

  Scenario: Should get two top cards from a shuffled deck
    When I play from the top
    Then I get 2 cards from the top from a shuffled deck
    And game cards are saved

  @ignore
  Scenario: Should get two bottom cards from a shuffled deck
    When I play from the bottom
    Then I get 2 cards from the bottom from a shuffled deck
    And game cards are saved

  Scenario: Should get a list of all games
    Given I have played games
    When I ask for all the games
    Then I get a list of all played games