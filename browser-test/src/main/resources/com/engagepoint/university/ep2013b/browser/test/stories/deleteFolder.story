Meta:

Narrative:
As a content management system (CMS) user
I want to delete folders in the repository
So that I can make changes in content

Scenario: User deletes all folders

Given opened 'http://localhost:18080/browser'
When clicks on './/*[contains(@id,'tree:0_2:treelink')]'
Then './/*[contains(@id,"table_data")]/tr[1]/td[2]' is not visible
When use right click on './/*[contains(@id,'tree:0_2:treelink')]'
Then './/*[contains(@id,"treeContextMenu")]' is visible on the page
When clicks on './/*[contains(@id,'contextItemDelete')]'
And clicks on './/*[contains(@id,'deletefolderbtn1')]'
Then './/*[contains(@id,'tree:0_2:treelink')]' is not visible
When clicks on './/*[contains(@id,'tree:0_2:treelink')]'
Then './/*[contains(@id,"table_data")]/tr[1]/td[2]' is not visible
When use right click on './/*[contains(@id,'tree:0_2:treelink')]'
Then './/*[contains(@id,"treeContextMenu")]' is visible on the page
When clicks on './/*[contains(@id,'contextItemDelete')]'
And clicks on './/*[contains(@id,'deletefolderbtn1')]'
Then './/*[contains(@id,'tree:0_2:treelink')]' is not visible