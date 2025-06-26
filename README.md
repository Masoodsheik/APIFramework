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
