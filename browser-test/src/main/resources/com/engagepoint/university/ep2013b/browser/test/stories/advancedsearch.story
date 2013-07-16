Meta:

Narrative:
As a content management system (CMS) user
I want to use advanced search
So that I can achieve a business goal

Scenario: User uses advanced search

Given opened 'http://localhost:18080/browser'
Then on the page the tree by xpath './/*[contains(@id,'tree:0_0:treelink')]' is visible
When use right click on './/*[contains(@id,'tree:0_0:treelink')]'
Then wait