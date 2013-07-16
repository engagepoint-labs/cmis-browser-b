Meta:

Narrative:
As a content management system (CMS) user
I want to use advanced search
So that I can achieve a business goal

Scenario: User uses advanced search

Given opened 'http://localhost:18080/browser'
When types 'Doc' on './/*[contains(@id,"searchInput")]'
And clicks on './/*[contains(@id,"searchbtn")]'
Then find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'