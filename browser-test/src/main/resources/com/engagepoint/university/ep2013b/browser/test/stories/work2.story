Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: User goes to the cmis-browser home page

Given user goes on page by url 'http://localhost:8080/browser'
Then page is shown

Scenario: User clicks tree leaf by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
Then in tree leaf by xpath './/*[contains(@id,'tree:0:treelink')]' is selected

Scenario: User clicks tree leaf by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks tree leaf by xpath './/*[text()='My_Folder-0-1']'
Then in tree leaf by xpath './/*[text()='My_Folder-0-1']' is selected

Scenario: User clicks tree leaf by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks tree leaf by xpath './/*[text()='My_Folder-0-0']'
Then in tree leaf by xpath './/*[text()='My_Folder-0-0']' is selected
