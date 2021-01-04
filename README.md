# Generate custom excel

How to generate custom excel in Java Spring Boot as a Maven project.

## Pre requisites

Before running the project, you need to:
1. Install Java 8
2. Install Maven
3. Install PostgreSQL 9.5 and import /dump/dump-users.sql in it. This script creates db, user db, table and adds some records in it.

## Compile
To compile the project, run the following command:
```bash
mvn clean install
```

## Run
To run the project, execute the following command:
```bash
java -jar excel-1.0.jar
```

## Usage
In the project, there is an API that can be used for testing the feature: the API will generate an excel like the one contained into /export/ folder.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.