Meta:

Narrative:
As a content management system (CMS) user
I want to use advanced search
So that I can find content by using advanced criterias

Scenario: User uses advanced search and find content

Given opened 'http://localhost:18080/browser'
When clicks on '//*[@id="form:royal:tabView"]/ul/li[2]/a'
And select 'document' in './/*[contains(@id,'searchDocumentType')]'
And clicks on './/*[contains(@id,'searchDateFrom')]/button'
And clicks on './/*[contains(@id,'ui-datepicker-div')]/table/tbody/tr[1]/td[2]/a'
And clicks on './/*[contains(@id,'searchDateTo')]/button'
And clicks on './/*[contains(@id,'ui-datepicker-div')]/table/tbody/tr[5]/td[4]/a'
And select 'text/plain' in './/*[contains(@id,'searchContentType')]'
And types 'Lorem' on './/*[contains(@id,'searchText')]'
And clicks on './/*[contains(@id,"advancedSearchBtn")]'
Then '/html/body/h1' is visible on the page
When clicks on './/*[contains(@id,'butnext')]'
Then '/html/body/h1' is visible on the page
When clicks on './/*[contains(@id,'butprev')]'
Then '/html/body/h1' is visible on the page
When clicks on './/*[contains(@id,'butlast')]'
Then '/html/body/h1' is visible on the page
When clicks on './/*[contains(@id,'butfirst')]'
Then '/html/body/h1' is visible on the page
When select '' in './/*[contains(@id,'searchDocumentType')]'
And clicks on './/*[contains(@id,"advancedSearchBtn")]'
Then '/html/body/h1' is visible on the page

Scenario: User uses advanced search and doesn't find content

Given opened 'http://localhost:18080/browser'
When clicks on '//*[@id="form:royal:tabView"]/ul/li[2]/a'
And select 'document' in './/*[contains(@id,'searchDocumentType')]'
And clicks on './/*[contains(@id,'searchDateFrom')]/button'
And clicks on './/*[contains(@id,'ui-datepicker-div')]/table/tbody/tr[1]/td[2]/a'
And clicks on './/*[contains(@id,'searchDateTo')]/button'
And clicks on './/*[contains(@id,'ui-datepicker-div')]/table/tbody/tr[5]/td[4]/a'
And types 'ILoveJSF' on './/*[contains(@id,'searchText')]'
And clicks on './/*[contains(@id,"advancedSearchBtn")]'
Then './/*[contains(@id,"table_data")]/tr[1]/td[2]' is not visible
When select '' in './/*[contains(@id,'searchDocumentType')]'
And clicks on './/*[contains(@id,"advancedSearchBtn")]'
Then find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'