Feature: Example1

    Scenario: Finding some capybara
       Given I am on the Google search page
       When I search for "Capivara"
       Then the page title should start with "capivara"
