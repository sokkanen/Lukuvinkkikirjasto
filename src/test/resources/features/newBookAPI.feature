Feature: A new book can be created with ISBN from API

    Scenario: creation is successful with title, author and ISBN in API overwrites title and author
        Given command new is selected
        When  title is "test", author is "C. reation", ISBN is "0-19-281602-0"
        Then  system will show book info: title "The prince" and author "Machiavelli, Niccolò"

    Scenario: creation with only ISBN adds title and author
        Given command new is selected
        When title is empty, author is empty, ISBN is "9789527144664"
        Then system will show book info: title "Iso O : matkaopas huipulle" and author "Kihlström, Marja"