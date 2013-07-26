Meta:

Narrative:
As a content management system (CMS) user
I want to create folders in the repository
So that I can change content

Scenario: User tries to create a new folder from context menu and click cancel

Given opened 'http://localhost:18080/browser'
When use right click on './/*[contains(@id,'tree:0:treelink')]'
Then './/*[contains(@id,"contextItemNewFolder")]' is visible on the page
When clicks on './/*[contains(@id,"contextItemNewFolder")]'
And types 'NewFolder' on './/*[contains(@id,'namefolder')]'
And clicks on './/*[contains(@id,'cancelForm')]'
Then './/*[@id="form:royal:tree:0_2:treelink"]' is not visible

Scenario: User creates a new folder from context menu and click save

Given opened 'http://localhost:18080/browser'
When use right click on './/*[contains(@id,'tree:0:treelink')]'
Then './/*[contains(@id,"contextItemNewFolder")]' is visible on the page
When clicks on './/*[contains(@id,"contextItemNewFolder")]'
And types 'NewFolder' on './/*[contains(@id,'namefolder')]'
And clicks on './/*[contains(@id,'savefolderbtn')]'
And clicks on './/*[@id="form:royal:tree:0:treelink"]'
Then './/*[@id="form:royal:tree:0_2:treelink"]' is visible on the page

Scenario: User tries to create a new folder with button menu and click cancel

Given opened 'http://localhost:18080/browser'
When clicks on './/*[contains(@id,"createfolder")]'
And types 'NewFolder2' on './/*[contains(@id,'namefolder')]'
And clicks on './/*[contains(@id,'cancelForm')]'
Then './/*[@id="form:royal:tree:0_3:treelink"]' is not visible

Scenario: User creates a new folder with button menu and click save

Given opened 'http://localhost:18080/browser'
When clicks on './/*[contains(@id,"createfolder")]'
And types 'NewFolder2' on './/*[contains(@id,'namefolder')]'
And clicks on './/*[contains(@id,'savefolderbtn')]'
And clicks on './/*[@id="form:royal:tree:0:treelink"]'
Then './/*[@id="form:royal:tree:0_3:treelink"]' is visible on the page
