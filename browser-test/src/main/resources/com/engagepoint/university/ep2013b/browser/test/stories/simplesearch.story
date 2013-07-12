Meta:

Narrative:
As a content management system (CMS) user
I want to use simple search

Scenario: In RootFolder (id 100) user clicks search input, types '-1', clicks search button and see 'My_Document-0-1' in table

Given opened 'http://localhost:18080/browser'
When types '-1' on './/input[contains(@id,"search")]'
And clicks on './/button[contains(@id,"search")]'
Then find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'


