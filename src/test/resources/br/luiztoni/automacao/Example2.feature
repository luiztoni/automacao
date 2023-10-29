Feature: Example2

    Scenario: Finding by Irecê Bahia
       Given I am on the Google page
       When I search for page "Wikipedia"
       And I click in Wikipedia link
       And I search for city "Irecê"
       Then the page title should start with word "Irecê"
