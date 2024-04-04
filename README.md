Technologies Covered in This Project
====================================

# Selenium
[Selenium](https://www.seleniumhq.org/) is for automating web applications for testing purposes.


# TestNG
[TestNG](https://testng.org/doc/) is a testing framework inspired from JUnit and NUnit but introducing some new functionalities that make it more powerful and easier to use.

Building and Running the Project
================================

## Prerequisites
There are a few things needed before you run the api.tests. Make sure you have the latest versions of the following installed:
- [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Maven](https://maven.apache.org/)

## Setup
1. Open/Clone the repo
2. Install dependencies with `mvn install`

## Run Web Tests via local driver

1. You can run web tests with the TestNG
2. You can run web tests with the Maven
3. You can run the XML by IDE or "mvn clean test -Dsurefire.suiteXmlFiles=testsuites/ParamTechWebTests.xml" command on terminal

## Run API Tests

1. You can run api tests with the TestNG
2. You can run api tests with the Maven
3. You can run the XML by IDE or "mvn clean test -Dsurefire.suiteXmlFiles=testsuites/ParamTechApiTests.xml" command on terminal

## Run All Tests

1. You can run web and api tests with the maven commandline: "mvn clean test"

## Allure Report
[Allure](https://allurereport.org/) is Automation Test Reporting Tool

1. Install Allure on your computer
2. Run tests
3. Generate and open allure report with this commandline: "allure serve allure-results"

## REST-assured
[Rest-assured](https://rest-assured.io/) is for API Automation