bookStoreAPI/
├── pom.xml
├── testng.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── App.java
│   │   └── resources/
│   │       ├── archetype-resources/
│   │       │   ├── pom.xml
│   │       │   └── src/main/java/App.java
│   │       └── META-INF/
│   │           └── maven/archetype.xml
│   └── test/
│       ├── java/
│       │   ├── bookStore/
│       │   │   ├── baseTest.java
│       │   │   ├── BookstoreApiTest.java
│       │   │   ├── createBook.java
│       │   │   ├── signup.java
│       │   │   └── bookStores.java
│       │   ├── context/
│       │   │   └── TestContext.java
│       │   ├── listeners/
│       │   │   ├── ExtentReportManagers.java
│       │   │   └── Setup.java
│       │   ├── Pojos/
│       │   │   ├── accessToken.java
│       │   │   ├── createBooks.java
│       │   │   └── signUp.java
│       │   └── utils/
│       │       └── restUtils.java
│       └── resources/
└── target/
    ├── classes/
    ├── generated-sources/
    ├── maven-archiver/
    ├── surefire-reports/
    ├── test-classes/
    └── test-output/
        └── ExtentReports/
            └── API_Automation_Report.html


Key Components:

pom.xml: Maven Project Object Model file, defining project dependencies and build configurations.

testng.xml: TestNG suite XML file, defining the test suite, test classes, and methods to be executed, along with listener configuration for ExtentReports.

src/test/java/bookStore/: Contains the core test classes.

baseTest.java: Base class for all API tests, setting up RestAssured.baseURI and initializing TestContext.

BookstoreApiTest.java: Contains the main API test methods for various bookstore functionalities.

createBook.java: Utility for creating createBooks POJO objects.

signup.java: Utility for creating signUp POJO objects and generating test data.

bookStores.java: Contains additional test methods related to user and book operations.

src/test/java/context/TestContext.java: Manages shared test context information, such as accessToken and tokenType, across different test methods.

src/test/java/listeners/ExtentReportManagers.java: Implements ITestListener to manage ExtentReports lifecycle, including report initialization, test start/finish, and logging test status.

src/test/java/Pojos/: Contains Plain Old Java Objects (POJOs) representing the request and response structures for API calls.

accessToken.java: POJO for access token details.

createBooks.java: POJO for book creation data.

signUp.java: POJO for user signup data.

src/test/java/utils/restUtils.java: A utility class providing generic methods for performing various HTTP requests (POST, GET, PUT, DELETE) with RestAssured.


