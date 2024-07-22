# Trello Board CRUD Operations Testing

## Table of Contents
- [Introduction](#introduction)
- [Test Plan](#test-plan)
- [Detailed Test Cases](#detailed-test-cases)
- [Automation Test Cases](#automation-test-cases)
- [Technologies Used](#technologies-used)
- [Setup Instructions for the Automation Tests](#setup-instructions-for-the-automation-tests)
- [How to Run All Tests](#how-to-run-all-tests)
- [How to Run UI Tests](#how-to-run-ui-tests)
- [How to Run API Tests](#how-to-run-api-tests)
- [Reporting](#reporting)
- [Assumptions, improvements and comments](#assumptions-improvements-and-comments)

## Introduction
This project focuses on testing the CRUD (Create, Read, Update, Delete) operations on Trello boards. The objective is to create comprehensive test cases covering both positive and negative scenarios, devise a test plan, and automate selected test cases for both the front-end and back-end.

## Test Plan

The test plan outlines the testing strategy and areas to be tested at both the back-end and front-end.

### Back-End Testing
- Ensure API endpoints for CRUD operations are functioning correctly.
- Confirm proper authentication and authorization mechanisms.
- Validate response to check if the operation has been done successfully.

### Front-End Testing
- Ensure that the Trello board operations such as creating, deleting, and updating boards function correctly through the user interface.
- Verifies that the UI elements for these operations are displayed properly, interact as expected, and provide appropriate feedback to the user.
- Validate proper input handling and error messages.

## Detailed Test Cases
 - **Create a Board**
 - Test Case 1: Create Board with Valid Name
 - Test Case 2: Create Board with Empty Name
 - Test Case 3: Create Board without Authentication
 
 - **Read a Board**
 - Test Case 1: Retrieve Board with Valid ID
 - Test Case 2: Retrieve Board with Invalid ID
 - Test Case 3: Retrieve Board without Authentication
 
 - **Update a Board**
 - Test Case 1: Update Board Name
 - Test Case 2: Update Board with Invalid ID
 - Test Case 3: Update Board without Authentication

 - **Delete a Board**
 - Test Case 1: Delete Board with Valid ID
 - Test Case 2: Delete Board with Invalid ID
 - Test Case 3: Delete Board without Authentication
 
 **UI Test Steps:**
   
##### Create Board - Positive scenario
   
 - 1. Login to Trello.
 - 2. Click on the "Create New Board" button.
 - 3. Enter a valid board name and select a background color.
Click "Create Board". **Expected Result**: The new board should appear on the dashboard with the specified name and background color.

##### Create Board - Negative scenario

 - 1. Login to Trello.
 - 2. Click on the "Create New Board" button.
 - 3. Leave the board name field empty..
Click "Create Board". **Expected Result**: Board should not be created with an empty name. A message should be displayed indicating that the board name cannot be empty.

##### View Board - Positive scenario
   
 - 1. Login to Trello.
 - 2. Open an existing board.
 - 3. Verify the board's name, background color, lists, and cards.
   **Expected Result**: The board details should match the saved configuration..

##### View Board - Negative scenario

 - 1. Login to Trello.
 - 2. Attempt to open a board with an invalid ID.
   **Expected Result**: An error message should be displayed, indicating that the board does not exist.

##### Update Board - Positive scenario
   
 - 1. Login to Trello.
 - 2. Open an existing board. 
 - 3. Click on the board name and edit it. Save the changes.
   **Expected Result**: The board name should be updated and displayed correctly.

##### Update Board - Negative scenario

  - 1. Login to Trello.
  - 2. Open an existing board. 
  - 3. Attempt to change the board name to an empty string. Save the changes.
   **Expected Result**: The board cannot be saved with an empty name.

##### Delete Board - Positive scenario
   
 - 1. Login to Trello.
 - 2. Click on "Show Menu" and select "More" > "Close Board".
 - 3. Confirm the deletion.
   **Expected Result**: The board should be removed from the dashboard.
 
 **API Test Steps:**

##### Create Board - Positive scenario
- **Description:** Create a board with a valid name and optional description.
- **Steps:**
  1. Send a POST request to the Trello API endpoint to create a board with api credentials.
  2. Include a valid name in the request payload.
- **Expected Result:** The board is created successfully, and a valid response containing the board's details is returned.

##### Create Board - Negative scenario
- **Description:** Create a board with a valid name and optional description.
- **Steps:**
  1. Send a POST request to the Trello API endpoint to create a board without including a name.
  **Expected Result:** The request fails, and an error message is returned indicating that the name is required.

##### Retrieve Board - Positive scenario
- **Description:** Retrieve a board using a valid board ID.
- **Steps:**
  1. Send a GET request to the Trello API endpoint with a valid board ID and api credentials.
- **Expected Result:** The board details are returned successfully.

##### Retrieve Board - Negative scenario
- **Description:** Retrieve a board using a invalid board ID.
- **Steps:**
  1. Send a GET request to the Trello API endpoint with a invalid board ID.
- **Expected Result:** The request fails, and an error message is returned indicating that the board was not found.

##### Update Board - Positive scenario
- **Description:** Update the name of an existing board.
- **Steps:**
  1. Send a PUT request to the Trello API endpoint with the board ID and new name.
- **Expected Result:** The board's name is updated successfully, and the updated board details are returned.

##### Update Board - Negative scenario
- **Description:** Update the name of an existing board with an invalid board ID.
- **Steps:**
  1. Send a PUT request with an invalid board ID.
- **Expected Result:** The request fails, and an error message is returned indicating that the board was not found.

##### Delete Board - Positive scenario
- **Description:** Delete a board using a valid board ID.
- **Steps:**
  1. Send a DELETE request to the Trello API endpoint with the board ID.
- **Expected Result:** The board is deleted successfully, and a confirmation response is returned.

##### Delete Board - Negative scenario
- **Description:** Delete a board using a valid board ID.
- **Steps:**
  1. Send a DELETE request to the Trello API endpoint with an invalid board ID.
- **Expected Result:** The request fails, and an error message is returned indicating that the board was not found.

Please refer [Atlassian Trello API Documentation - Actions](https://developer.atlassian.com/cloud/trello/rest/api-group-actions/#api-group-actions)
for the endpoints.

## Automation Test Cases
The above CRUD operations on Trello boards have been automated for both UI and API, ensuring comprehensive coverage of create, read, update, and delete functionalities.

## Technologies Used
 - Selenium: For UI automation
 - POSTMAN: For checking the API Endpoints
 - RestAssured - For API automation
 - Cucumber BDD JUnit - Framework
 - Maven - Build

## Setup Instructions for the Automation Tests

 **Prerequisites**
 
 - JDK (Version 21.0.4)
 - Maven (Version 3.9.8)
 - Cucumber Eclipse Plugin (Version 1.0.0.202106240526)
 - Selenium (Version 3.141.59)
 - RestAssured (Version 4.4.0)
 - IDE (I have used Eclipse)
 
 **Ensure that the necessary dependencies are included in your build file.**
 
 **Setup Instructions**
 - Clone the repository
 - Open the root directory on cmd
 - If you want to run all tests
 
 ```bash
mvn test
 ```

## How to Run All Tests

 - Through maven
 
 ```bash
mvn test
 ```
 - TestRunner.java (run as JUnit Test)

## How to Run UI Tests

  - TestRunnerUI.java (run as JUnit Test)

## How to Run API Tests

  - TestRunnerAPI.java (run as JUnit Test)
  
## Reporting

  - The test reports are stored in the `target` folder as `index.html`. To view the reports, open the `index.html` file in a web browser.  
  
## Assumptions, improvements and comments  

  - Tests have been run on Windows using Chrome, for running the tests on Linux, ensure that the appropriate WebDriver for Chrome is installed and configured.
  - Before each test Trello Board is created and then after execution it is removed (for both API and Selenium tests).
  - I assumed that my Trello account on which tests are executed is hermetic environment. This means that in the meantime no one is using it nor creating there boards. Such actions might impact tests results.
  - Please note that I have a Trello Premium free trial and that is valid for 9 more days from today(22-07).
  - I have pulled images for hub(selenium standalone) and node(chrome) and tried to docker-compose yaml file, however when I attempted to run the tests on docker I got SessionNotCreated exception. This could be due to version incompatibilities. I intend to spend more time on time and fix the issue.
