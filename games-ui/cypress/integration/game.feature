Feature: Games

    Background: Mock Microservices
        Given I have setup games microservice

    Scenario: Should display Game strings
        When that I open the game page
        Then I can see the text "Card Player"
        And I can see the text "Past Games!"

    Scenario: Should see basic elements when opening game app
        When that I open the game page
        Then I can see these elements on the screen
            | elements             |
            | Card Player          |
            | Past Games!          |
            | Play Cards           |
            | Play From The Bottom |
            | Filter               |

    Scenario: Should display two cards from the top of the deck
        Given that I open the game page
        When I click on the "Play Cards" button
        Then the game displays the two played cards
        And history should be updated with cards game