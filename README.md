veraPDF for WCAG tests
-------------------------------------------------------------

Quick Start
-----------
### Pre-requisites

In order to run tests you'll need:

 * Java 11+, which can be downloaded [from Oracle](https://www.oracle.com/java/technologies/downloads/)
 * [Maven v3+](https://maven.apache.org/install.html)
 * The Chrome browser [built by Google](https://www.google.com/chrome/)
 * 4 Gb RAM on VM or PC with OS (Windows 10 or higher, Linux Ubuntu 20.04 or higher)  where tests will be run
 * Screen resolution at least 1024x768

### Running tests:
1. To run Selenium tests - check out the project to a local directory
2. Run tests using Maven: `mvn clean install`

### Additional Examples how to start project:
1. `mvn clean install`  General profile active by default with baseUrl = https://dev.pdf4wcag.duallab.com/, see property file
2. `mvn test -P GeneralTests -DbaseUrl=https://dev.pdf4wcag.duallab.com/`
3. `mvn test -P HotTests -DbaseUrl=https://dev.pdf4wcag.duallab.com/`    Profile Hot tests
4. `mvn test -P DemoTests -DbaseUrl=https://dev.pdf4wcag.duallab.com/`   Profile Demo tests
5. `mvn test -Dtest=JobTest -DbaseUrl=https://dev.pdf4wcag.duallab.com/`
6. `mvn clean install -Dtest=JobTest#addNewJob`
7. `mvn clean install -Dtest=JobTest,HotTest`

### Checking  results
When the tests are finished, the folder 'target' contains:
1. tests_log.log file with information on each test
2. surefire-reports subfolder with emailable-report
3. screenshots subfolder with screenshot files in case if test(s) are SKIP or FAILURE


### Tests list and brief description

1. MainPageTest#checkMainPageGUI - the first test to check a webelement on the main page.
2. DemoTest#demoCheckProfile - the test to check a "Demo Suite".
3. HotTest#hotCheckProfile  - the test to check a "Smoke Suite".
4. ... to be continued

