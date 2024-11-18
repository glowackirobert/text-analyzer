# Project

This is demo project to analyze user input to check every occurrence of query in it.

## Requirement

Java21
Maven

### Build the project:

`mvn clean package`


### Run the application:

`java -jar target/text-analyzer-0.0.1-SNAPSHOT.jar`

### API Documentation
GET /api/v1/search

Parameters:
- input (string): The input text to search within.
- query (string): The text to be queried.

Response:
Returns a JSON object containing the list of words to be highlighted.