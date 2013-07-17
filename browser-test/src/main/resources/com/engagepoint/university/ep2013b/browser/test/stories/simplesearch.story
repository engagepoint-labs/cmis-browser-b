Meta:

Narrative:
As a content management system (CMS) user
I want to use simple search
So that I can achieve a business goal

Scenario: User uses simple search

Given opened 'http://localhost:18080/browser'
When types 'Doc' on './/*[contains(@id,"searchInput")]'
And clicks on './/*[contains(@id,"searchbtn")]'
Then find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'

Scenario: User uses simple search and return to the previous state

Given opened 'http://localhost:18080/browser'
When types 'Doc' on './/*[contains(@id,"searchInput")]'
And clicks on './/*[contains(@id,"searchbtn")]'
Then find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'
When use backspace in './/*[contains(@id,"searchInput")]'
And clicks on './/*[contains(@id,"searchbtn")]'
Then find 'My_Document-0-0' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'
And find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[2]'

Scenario: User checks next and previous page after search

Given opened 'http://localhost:18080/browser'
When types 'Doc' on './/*[contains(@id,"searchInput")]'
And clicks on './/*[contains(@id,"searchbtn")]'
Then find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'
When clicks on './/*[contains(@id,'table:butnext')]'
Then find 'My_Document-0-2' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'
When clicks on './/*[contains(@id,'butprev')]'
Then find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'

Scenario: User checks last and first page after search

Given opened 'http://localhost:18080/browser'
When types 'Doc' on './/*[contains(@id,"searchInput")]'
And clicks on './/*[contains(@id,"searchbtn")]'
Then find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'
When clicks on './/*[contains(@id,'butlast')]'
Then find 'My_Document-0-2' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'
When clicks on './/*[contains(@id,'butfirst')]'
Then find 'My_Document-0-1' on '(.//*[contains(@id,"table") and contains(@id,"itemname")])[1]'