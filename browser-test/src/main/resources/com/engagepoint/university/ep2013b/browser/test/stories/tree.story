Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: User goes to the cmis-browser home page

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
Then page is shown

Scenario: User clicks tree leaf by xpath

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks tree leaf by xpath './/*[@id='form:tree:0:link']'
Then in tree leaf by xpath './/*[@id='form:tree:0:link']' is selected

Scenario: User clicks tree leaf by name

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks tree leaf by name 'form:tree:1:link'
Then in tree leaf by name 'form:tree:1:link' is selected

Scenario: User clicks tree leaf by id

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks tree leaf by id 'form:tree:2:link'
Then in tree leaf by id 'form:tree:2:link' is selected

Scenario: User expands tree root by xpath

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks tree leaf expand by xpath './/*[@id='form:tree:0']//*[contains(@class,'ui-tree-toggler')]'
Then tree leaf by xpath './/*[@id='form:tree:0']//*[contains(@class,'ui-tree-toggler')]' is expanded

Scenario: User clicks tree root child leaf by id

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks tree leaf expand by xpath './/*[@id='form:tree:1']//*[contains(@class,'ui-tree-toggler')]'
And user clicks tree leaf by id 'form:tree:1_0:link'
Then in tree leaf by id 'form:tree:1_0:link' is selected

Scenario: User expands tree leaf by xpath

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks tree leaf expand by xpath './/*[@id='form:tree:1']//*[contains(@class,'ui-tree-toggler')]'
Then tree leaf by xpath './/*[@id='form:tree:1']//*[contains(@class,'ui-tree-toggler')]' is expanded