Feature: A book can be marked as read or unread

  Scenario: a book can be marked read
    Given command list all is selected
    And   book with title "The super coder 2", author "GirlCoder" and ISBN "0-2479-5474-8" is added to reading tips
    When  book with title "The super coder 2" is marked as read
    Then  system will not show book info: title "The super coder 2" and "Mark as read"

  Scenario: a book can be marked unread
    Given command list all is selected
    And   book with title "The super coder 2", author "GirlCoder" and ISBN "0-2479-5474-8" is added to reading tips
    When  book with title "The super coder 2" is marked as read
    And   book with title "The super coder 2" is marked as unread
    Then  system will show book info: title "The super coder 2" and "Mark as read"
