Feature: A new book can be created if proper info is given

    Scenario: creation is successful with valid title, author and ISBN 
        Given command new is selected
        When  title is "test title", author is "C. reation", ISBN is "9519854893"
        Then  system will respond with "test title"
        And   system will respond with "C. reation"
        And   system will respond with "9519854893"
    
    Scenario: creation fails with empty input
        Given command new is selected
        When  title, author and ISBN is empty
        Then  system will respond with "Title must be between 2 and 30 characters"
        And   system will respond with "Author name must be between 2 and 30 characters"
        And   system will respond with "ISBN length must be between 10 and 13"

    Scenario: creation fails with empty title
        Given command new is selected
        When  title is empty, author is "C. reation", ISBN is "9519854894"
        Then  system will respond with "Title must be between 2 and 30 characters"

    Scenario: creation fails with empty author 
        Given command new is selected
        When  title is "Best book ever", author is empty, ISBN is "9519854894"
         Then system will respond with "Author name must be between 2 and 30 characters"

    Scenario: creation fails with empty ISBN
        Given command new is selected
        When  title is "Best book ever", author is "C. reation", ISBN is empty
        Then  system will respond with "ISBN length must be between 10 and 13"

    Scenario: creation fails with invalid ISBN
        Given command new is selected
        When  title is "Best book ever", author is "C. reation", ISBN is "95131231239854894"
        Then  system will respond with "ISBN length must be between 10 and 13"