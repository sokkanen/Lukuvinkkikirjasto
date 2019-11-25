Feature: A new book can be created if proper info is given

    Scenario: creation is successful with valid title, author and ISBN
        Given page with book creation is selected
        When  title is "Nineteen Eighty-Four", author is "George Orwell", ISBN is "9780141187761"
        Then  page will list book with the title "Nineteen Eighty-Four", author "George Orwell" and ISBN "9780141187761"
#
#    Scenario: creation fails with empty input
#        Given command new is selected
#        When  title, author and ISBN is empty
#        Then  system will respond with "fill out needed information"
#
#    Scenario: creation fails with empty title
#        Given command new is selected
#        When  title is empty, author is "C. reation", ISBN is "951-98548-9-4"
#        Then  system will respond with "fill out needed information"
#
#    Scenario: creation fails with empty author
#        Given command new is selected
#        When  title is "Best book ever", author is empty, ISBN is "951-98548-9-4"
#         Then system will respond with "fill out needed information"
#
#    Scenario: creation fails with empty ISBN
#        Given command new is selected
#        When  title is "Best book ever", author is "C. reation", ISBN is empty
#        Then  system will respond with "fill out needed information"
#
#    Scenario: creation fails with invalid ISBN
#        Given command new is selected
#        When  title is "Best book ever", author is "C. reation", ISBN is "951-98548-9-3"
#        Then  system will respond with "invalid ISBN number"