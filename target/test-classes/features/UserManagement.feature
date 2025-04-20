@Dashboard
Feature: User Management
  As an admin
  I want to navigate to the "View & Create Users" form from the dashboard
  So that I can add a new user to the system.

  Background: 
    Given Admin is logged into the dashboard page

  Scenario: Navigate to the User Management page
    When the admin clicks on the "Users" menu
    And selects "View & Create Users" from the dropdown
    Then the system should display the "Manage Users" page
    And the page should show a list of existing users
    And the page should have an "Add User" button

  Scenario: Open the Add User form
    Given the admin is on the "Manage Users" page
    And the admin clicks on the "Add User" button
    Then the "Add User" form should be displayed with fields:
      | Name             |
      | Email            |
      | Roles            |
      | Password         |
      | Confirm Password |
    And the form should have status options:
      | Active   |
      | Disabled |

  Scenario Outline: Create a new user successfully
    Given the admin is on the "Manage Users" page
    And the admin clicks on the "Add User" button
    Then the admin enters:
      | Name             | <name>     |
      | Email            | <email>    |
      | Roles            | <role>     |
      | Password         | <password> |
      | Confirm Password | <password> |
    And selects status "<status>"
    And the admin clicks the "Add User" button
    Then A toast message should should appear celebrating

    Examples: 
      | name       | email          | role  | password | status   |
      | test user  | test1@mail.com | Admin | pass1234 | Active   |
      | anotherOne | user2@mail.com | Admin | 1234pass | Disabled |

  Scenario Outline: Delete an existing user
    Given the admin is on the "Manage Users" page
    And a user named "<username>" exists in the list
    When the admin clicks the gear icon for "<username>"
    And selects the "Remove" option
    Then the system should prompt for confirmation
    When the admin confirms the deletion
    Then the user "<username>" should no longer appear in the list

    Examples: 
      | username   |
      | test user  |
      | anotherOne |
