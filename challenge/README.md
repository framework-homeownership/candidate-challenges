# Challenge Instructions

NOTE: In lieu of requiring you to use a local database, the test content for this task has been provided as a JSON document called `content.json` located in the resources folder of this project (src/main/resources/content.json).  You will need to read and parse this document and store it temporarily while the program is running in order to test your code.

# Setup

You are being provided with the Jackson Databind library and Spring-Boot-Starter-Test as a dependencies in the pom.xml file for this project.  Feel free to add other dependencies if you prefer using something different.

This Spring-Boot project includes Maven Wrapper, so you do not need to have Maven installed on your machine to run the project.  To run this project from the command line with Maven Wrapper you may use the command:

`./mvnw spring-boot:run`

To build the project and run tests you can use:

`./mvnw compile install`

# Instructions

A Library is a collection of information in various forms.  It can contain books, magazines, videos, newspapers, or any number of other sources of information (i.e. maps, microfilm, etc.).  That means that a Library can be represented as a collection of Content.

All Content has a distinct Type, which can be any of the following, although these are just a few of many possible examples:

Fiction, Fantasy, Non-Fiction, History, Poetry, Scholarly Journals, Video, Audio…and so on.

All Content also has Title and a unique Id for tracking and identification purposes.

You have been given some starter code for the Content class.  You task is to implement the ILibrary interface in a Library class to store and retrieve content using the existing Content class.  You will also need to delete content from the Library (you do not need to delete anything from the json file itself).

You are free to make your own design decisions about anything that seems unclear or ambiguous, but you may not change the ILibrary interface directly.  You are free to add any additional functionality that seems missing or needed, but you should document any of these design decisions clearly and not spend extra time on any features that were not requested.  You will be judged on your design, code style, and the functionality of your code.  

Please include sufficient tests to verify that your code functions as expected.

