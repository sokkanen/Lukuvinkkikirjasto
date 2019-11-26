Feature: All reading tips can be listed

  Scenario: empty list can be listed
    Given command list all is selected
    When  database is empty
    Then  system will respond with "You have no reading tips"

  Scenario: list with one book can be listed
    Given command list all is selected
    When  book with title "The super coder", author "GirlCoder" and ISBN "978-952-5802-04-7" is added to reading tips
    Then  system will respond with book info: title "The super coder", author "GirlCoder" and ISBN "978-952-5802-04-7"

  Scenario: list with three books can be listed
    Given command list all is selected
    When  book with title "The super coder", author "GirlCoder" and ISBN "978-952-5802-04-7" is added to reading tips
    And   book with title "Amazing java", author "ManCoder" and ISBN "978-952-5802-11-5" is added to reading tips
    And   book with title "Null", author "BoyCoder" and ISBN "978-0-575-08143-7" is added to reading tips
    Then  system will respond with book info: title "The super coder", author "GirlCoder" and ISBN "978-952-5802-04-7"
    Then  system will respond with book info: title "Amazing java", author "ManCoder" and ISBN "978-952-5802-11-5"
    Then  system will respond with book info: title "Null", author "BoyCoder" and ISBN "978-0-575-08143-7"
