Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: User goes to the cmis-browser home page

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
Then page is shown

Scenario: User see the tree on the page by xpath

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
Then on the page the tree by xpath './/*[contains(@id,'tree')]' is visible

Scenario: User see the table on the page by xpath

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
Then on the page the table by xpath './/*[contains(@id,'table')]' is visible

Scenario: User clicks tree folder by xpath

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks in the tree folder by xpath './/*[@id='form:tree:0:link']'
Then in the tree folder by xpath './/*[@id='form:tree:0:link']' is selected

Scenario: User expands tree folder by xpath

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks tree folder toggler by xpath './/*[@id='form:tree:0']//*[contains(@class,'ui-tree-toggler')]'
Then in the tree folder by xpath './/*[@id='form:tree:0']//*[contains(@class,'ui-tree-toggler')]' is expanded

Scenario: User clicks tree folder child by xpath

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks tree folder toggler by xpath './/*[@id='form:tree:1']//*[contains(@class,'ui-tree-toggler')]'
And user clicks in the tree folder by xpath 'form:tree:1_0:link'
Then in the tree folder by xpath 'form:tree:1_0:link' is selected

