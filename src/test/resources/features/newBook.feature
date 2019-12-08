Feature: A new book can be created if proper info is given

    Scenario: creation is successful with valid title, author and ISBN
        Given command new is selected
        When  title is "test book", author is "C. reation", ISBN is "0-4180-5201-8"
        Then  system will show book info: title "test book" and author "C. reation"

    Scenario: creation fails with empty input
        Given command new is selected
        When  title, author and ISBN is empty
        Then  system will respond with "Must inlude either Title or valid ISBN"

    Scenario: creation works with empty title
        Given command new is selected
        When  title is empty, author is "C. reation", ISBN is "0-6676-1426-5"
        Then  system will respond with "Additional information not found"

    Scenario: creation is successful with empty author
        Given command new is selected
        When  title is "Best book 4ever", author is empty, ISBN is "0-4284-4335-4"
        Then  system will respond with "Best book 4ever"

    Scenario: creation is successful with empty ISBN
        Given command new is selected
        When  title is "Best book ever", author is "C. reation", ISBN is empty
        Then  system will respond with "Best book ever"
        And   system will respond with "C. reation"

    Scenario: creation fails with too long ISBN
        Given command new is selected
        When  title is "Best book ever", author is "C. reation", ISBN is "978-952-5802-04-72"
        Then  system will respond with "Invalid ISBN"
