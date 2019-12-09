Feature: A book can be marked as read or unread

  Scenario: a book can be marked read
    Given command mark as read is selected
    When book exists
    Then book is marked as read