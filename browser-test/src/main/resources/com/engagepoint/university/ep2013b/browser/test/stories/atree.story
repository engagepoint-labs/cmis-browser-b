Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: User goes to the cmis-browser home page

Given user goes on page by url 'http://localhost:18080/browser/browser.xhtml'
Then page is shown

Scenario: User see the tree on the page by xpath

Given user goes on page by url 'http://localhost:18080/browser/browser.xhtml'
Then on the page the tree by xpath './/*[contains(@id,'tree:0')]' is visible

Scenario: User see the table on the page by xpath

Given user goes on page by url 'http://localhost:18080/browser/browser.xhtml'
Then on the page the table by xpath './/*[contains(@id,'table')]' is visible

Scenario: User clicks tree folder by xpath

Given user goes on page by url 'http://localhost:18080/browser/browser.xhtml'
When user clicks in the tree folder by xpath './/*[contains(@id,'tree:0_0:treelink')]'
Then in the tree folder by xpath './/*[contains(@id,'tree:0_0:treelink')]' is selected

Scenario: User expands tree folder by xpath

Given user goes on page by url 'http://localhost:18080/browser/browser.xhtml'
When user clicks in the tree folder by xpath './/*[contains(@id,':tree:0_0:treelink')]'
And user clicks tree folder toggler by xpath './/*[contains(@id,'tree:0_0_1')]//*[contains(@class,'ui-tree-toggler')]'
Then in the tree folder by xpath './/*[contains(@id,'tree:0_0_1')]//*[contains(@class,'ui-tree-toggler')]' is expanded

Scenario: User clicks tree folder child folder by xpath

Given user goes on page by url 'http://localhost:18080/browser/browser.xhtml'
When user clicks tree folder toggler by xpath './/*[contains(@id,'tree:0_0')]//*[contains(@class,'ui-tree-toggler')]'
And user clicks in the tree folder by xpath './/*[contains(@id,'tree:0_0_1:treelink')]'
Then in the tree folder by xpath './/*[contains(@id,'tree:0_0_1:treelink')]' is selected

