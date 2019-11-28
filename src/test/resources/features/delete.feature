Feature: Reading tips can be deleted

  Scenario: a book can be deleted
    Given command list all is selected
    And   book with title "The super coder", author "GirlCoder" and ISBN "978-952-5802-04-7" is added to reading tips
    When  book with title "The super coder" is deleted
    Then  system will respond with "You have no reading tips"