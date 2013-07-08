Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: User see tree folder children in the table by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks in the tree folder by xpath './/*[@id='form:tree:1:link']'
Then in the table folder child by xpath './/*[contains(@id,'table:1:itemname') and text()='My_Document-0-1']' is visible

Scenario: User see tree folder children in the table by xpath - 2

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks in the tree folder by xpath './/*[@id='form:tree:2:link']'
Then in the table folder child by xpath './/*[contains(@id,'table:1:itemname') and text()='My_Document-0-1']' is visible

Scenario: User see next page of tree folder children in the table by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks in the tree folder by xpath './/*[@id='form:tree:0:link']'
Then in the table folder child by xpath './/*[contains(@id,'table:1:itemname') and text()='My_Document-0-1']' is visible

Scenario: User see previos page of tree folder children in the table by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks in the tree folder by xpath './/*[@id='form:tree:0:link']'
Then in the table folder child by xpath './/*[contains(@id,'table:1:itemname') and text()='My_Document-0-1']' is visible

Scenario: User see first page of tree folder children in the table by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks in the tree folder by xpath './/*[@id='form:tree:0:link']'
Then in the table folder child by xpath './/*[contains(@id,'table:1:itemname') and text()='My_Document-0-1']' is visible

Scenario: User see last page of tree folder children in the table by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks in the tree folder by xpath './/*[@id='form:tree:0:link']'
Then in the table folder child by xpath './/*[contains(@id,'table:1:itemname') and text()='My_Document-0-1']' is visible

