Feature: A new book can be created if proper info is given

    Scenario: creation is successful with valid title, author and ISBN
        Given command new is selected
        When  title is "test book", author is "C. reation", ISBN is "9789515785817"
        Then  system will respond with book info: title "test book", author "C. reation" and ISBN "9789515785817"

    Scenario: creation fails with empty input
        Given command new is selected
        When  title, author and ISBN is empty
        Then  system will respond with "Title must be between 2 and 30 characters"

    Scenario: creation fails with empty title
        Given command new is selected
        When  title is empty, author is "C. reation", ISBN is "978-952-5802-04-7"
        Then  system will respond with "Title must be between 2 and 30 characters"

    Scenario: creation is successful with empty author
        Given command new is selected
        When  title is "Best book 4ever", author is empty, ISBN is "9789515785817"
        Then  system will respond with "Best book 4ever"
        And   system will respond with "9789515785817"

    Scenario: creation is successful with empty ISBN
        Given command new is selected
        When  title is "Best book ever", author is "C. reation", ISBN is empty
        Then  system will respond with "Best book ever"
        And   system will respond with "C. reation"

    Scenario: creation fails with invalid ISBN
        Given command new is selected
        When  title is "Best book ever", author is "C. reation", ISBN is "978-952-5802-04-72"
        Then  system will respond with "Invalid ISBN"