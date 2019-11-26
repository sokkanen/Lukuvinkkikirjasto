Feature: All reading tips can be listed

  Scenario: empty list can be listed
    Given command list all is selected
    When  database is empty
    Then  system will respond with "You have no reading tips"

  Scenario: list with one book can be listed
    Given command list all is selected
    When  book with title "The super coder", author "GirlCoder" and ISBN "9519854894" is added to reading tips
    Then  system will respond with book info: title "The super coder", author "GirlCoder" and ISBN "9519854894"

  Scenario: list with three books can be listed
    Given command list all is selected
    When  book with title "The super coder", author "GirlCoder" and ISBN "9519854894" is added to reading tips
    And   book with title "Amazing java", author "ManCoder" and ISBN "0754606627" is added to reading tips
    And   book with title "Null", author "BoyCoder" and ISBN "0852105266" is added to reading tips
    Then  system will respond with book info: title "The super coder", author "GirlCoder" and ISBN "9519854894"
    Then  system will respond with book info: title "Amazing java", author "ManCoder" and ISBN "0754606627"
    Then  system will respond with book info: title "Null", author "BoyCoder" and ISBN "0852105266"
