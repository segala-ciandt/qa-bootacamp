Feature: Dealer

  Scenario: Should shuffle a deck obtained from deck
    When Game requests a shuffled deck
    Then Game gets a shuffled deck