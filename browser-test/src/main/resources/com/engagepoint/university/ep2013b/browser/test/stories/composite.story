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

Scenario: User see tree leaf children in the table by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
Then in the table child by xpath './/*[contains(@id,'table:1:itemname') and text()='My_Document-0-1']' is visible

Scenario: User expands tree root by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks tree leaf expand by xpath './/*[contains(@id,'tree:0:treelink')]//*[contains(@class,'ui-tree-toggler')]'
Then tree leaf by xpath './/*[contains(@id,'tree:0:treelink')]//*[contains(@class,'ui-tree-toggler')]' is expanded

Scenario: User clicks tree root child leaf by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks tree leaf expand by xpath './/*[@id='form:tree:1']//*[contains(@class,'ui-tree-toggler')]'
And user clicks tree leaf by xpath './/*[@id='form:tree:1']'
Then in tree leaf by xpath './/*[@id='form:tree:1']' is selected

Scenario: User clicks tree root child leaf by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks tree leaf expand by xpath './/*[@id='form:tree:1']//*[contains(@class,'ui-tree-toggler')]'
And user clicks tree leaf by xpath './/*[@id='form:tree:1']'
Then in tree leaf by xpath './/*[@id='form:tree:1']' is selected

Scenario: User expands tree leaf by xpath

Given user goes on page by url 'http://localhost:8080/browser/browser.xhtml'
When user clicks tree leaf expand by xpath './/*[@id='form:tree:1']//*[contains(@class,'ui-tree-toggler')]'
Then tree leaf by xpath './/*[@id='form:tree:1']//*[contains(@class,'ui-tree-toggler')]' is expanded