
Here's a template for your README.md file:

Markdown

# BookStore API Automation
## Project Structure

The project is organized as follows:

src/main/java
├── bookStore                 # Contains test classes and helper classes for Bookstore API
│   ├── baseTest.java         # Base class for API tests, sets up RestAssured base URI and TestContext.
│   ├── BookstoreApiTest.java # Main test class for CRUD operations on books.
│   ├── createBook.java       # Helper for creating CreateBooks POJO instances.
│   └── signup.java           # Helper for creating SignUp POJO instances.
├── context                   # Manages shared test context (e.g., authentication tokens)
│   └── TestContext.java      # Stores and provides access tokens and authorization headers.
├── endPoints                 # Centralized API endpoint definitions
│   └── endPoints.java        # Defines all base URLs and specific API paths.
├── Pojos                     # Plain Old Java Objects (POJOs) for request/response bodies
│   ├── accessToken.java      # POJO for access token response.
│   ├── createBooks.java      # POJO for book creation/update request/response.
│   └── signUp.java           # POJO for user signup request.
├── Reportlisteners           # Custom TestNG listeners for Extent Reports
│   ├── ExtentReportManagers.java # Manages ExtentReports generation and logging.
│   └── Setup.java            # Another listener (potentially for additional report setup).
├── testData                  # Static test data
│   └── BooksDetails.java     # Contains static test data for books.
└── utils                     # Reusable utility methods for API interactions
└── restUtils.java        # Generic methods for making HTTP requests (POST, GET, PUT, DELETE).


## Technologies Used

    * **Java 11+**
    * **Rest-Assured**: For simplified REST API testing.
    * **TestNG**: Testing framework.
    * **Maven**: Build automation tool.
    * **Lombok**: Reduces boilerplate code for POJOs.
    * **ExtentReports**: For generating comprehensive test reports.
    * **Jackson/Gson (Transitive Dependency)**: For JSON serialization/deserialization with POJOs.
    * **Faker**: For generating realistic test data.

## Setup and Installation

### Prerequisites

    * Java Development Kit (JDK) 11 or higher installed.
    * Maven installed.
    * An IDE like IntelliJ IDEA or Eclipse.
    * Ensure your local BookStore API server is running at `http://127.0.0.1:8000` as configured in `endPoints.java`.

### Cloning the Repository

    ```bash
    git clone <repository-url>
    cd bookstore-api-automation
    Building the Project
    Navigate to the root directory of the project (where pom.xml is located) and run:

Bash
    
    mvn clean install
    This command will download all necessary dependencies and build the project.

Configuration
    API base URL and endpoints are configured in src/main/java/endPoints/endPoints.java.
    
    baseURL: http://127.0.0.1:8000
    
    loginEndpoint: /login
    
    signUpEndPoints: /signup
    
    bookEndpoint: /books/
    
    putEndpoint: /books/
    
    deleteEndpoint: /books/

You can modify these if your API server is hosted elsewhere or has different paths.

Running Tests
    Tests are written using TestNG.

Running all tests
  To run all tests, you can use Maven:

Bash

mvn test
  Running specific tests
  You can run specific test classes or methods via your IDE (e.g., right-click on BookstoreApiTest.java and select "Run Tests").

Reporting
  Test execution reports are generated using ExtentReports. After running the tests, an HTML report will be generated at:
  
  test-output/ExtentReports/API_Automation_Report.html

This report provides a detailed summary of test passes, failures, skips, and execution times.

API Endpoints Tested
    The current test suite covers the following BookStore API functionalities:
    
    User Registration: POST /signup
    
    User Login: POST /login
    
    Create Book: POST /books/
    
    Get Book Details: GET /books/{id} (Implicitly tested when getting all books or a specific book)
    
    Update Book Details: PUT /books/{id}
    
    Delete Book: DELETE /books/{id}

Test Data Management
Static Test Data: Located in src/main/java/testData/BooksDetails.java. This class holds static values for book properties.

Dynamic Test Data: src/main/java/bookStore/signup.java uses Faker to generate dynamic email and password for signup, ensuring unique test runs.

POJOs: Request and response bodies are strongly typed using POJOs defined in src/main/java/Pojos/.

POJOs
POJOs (Plain Old Java Objects) are used to represent JSON request and response bodies, providing type safety and easier data manipulation.

Pojos/signUp.java: Represents the structure for user registration requests.

Pojos/createBooks.java: Represents the structure for creating and updating book details.

Pojos/accessToken.java: Represents the structure for the login response, holding the access token and token type.

Utility Methods
src/main/java/utils/restUtils.java contains generic utility methods to interact with the API, simplifying test method implementations. These methods handle:

performPostRequest: Overloaded methods to send POST requests with different payload types (String, Map, Object).

performPostRequestWithAuthorization: POST requests requiring an authorization header.

getReponse: GET requests with authorization.

putResponse: PUT requests with payload and authorization.

deleteRequest: DELETE requests with authorization.

Contributing
Feel free to contribute to this project by:

Reporting bugs.

Suggesting new features.

Improving existing tests or adding new ones.

Refactoring code for better readability and maintainability.


