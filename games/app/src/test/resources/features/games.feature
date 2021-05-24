Feature: Games

  Scenario: Should get shuffled deck from the dealer
    When I play from the top
    Then I get 2 cards from the top from a shuffled deck
    And game cards are saved

