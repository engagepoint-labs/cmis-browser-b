Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: User goes to the Home page

Given user is on Home page
Then Find title Hallo user


Scenario: User clicks tree root

Given user is on Home page
When user clicks tree root
Then root is selected

Scenario: User expands tree root

Given user is on Home page
When user clicks root expand .//*[@id='form:tree:0']/span/span[1]
Then root is expanded by path './/*[@id='form:tree:0' and @aria-expanded='true']'