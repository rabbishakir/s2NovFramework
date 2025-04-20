@Login
Feature: Testing Login Capabilities
  Login to the website to a dashboard.

  Background: 
    Given Automation tools are loaded and running
    And User is on the login page

  @positive
  Scenario Outline: Login Success with valid credentials
    When the user enters username "<username>" and password "<password>"
    And clicks the "Login" button
    Then the system should display "<outcome>".

    Examples: 
      | username        | password | outcome   |
      | admin@admin.com |   123456 | Dashboard |

  @negative
  Scenario Outline: Login Fail with invalid credentials
    When the user enters username "<username>" and password "<password>"
    And clicks the "Login" button
    Then the system should display "<outcome>".

    Examples: 
      | username        | password  | outcome                                     |
      | admin@admin.com | wrongpass | These credentials do not match our records. |
      | invalid@mail.com   | wrongpass    | These credentials do not match our records.                                |
      |                    | validpass123 | Please fill out this field                                                 |
      | validuser@mail.com |              | Please fill out this field                                                 |
#      | invalid            | password     | Please include an @ in the email address. "<username>" is missing an @ |
