# Wordsworth

## Description
Wordsworth is a Java-based web application built with Spring Boot that provides flashcard functionality. The project aims to help users create, manage, and review flashcards for effective learning.

## Prerequisites
- Java 17 or higher
- Maven 3.6.0 or higher
- PostgreSQL (for runtime database)

## Setup Instructions

### Clone the Repository
```sh
git clone https://github.com/dbronshteyn/Wordsworth.git
cd Wordsworth
```

### Build the Project
Ensure that you have Maven and Java installed on your machine. Then, build the project using the following command:
```sh
mvn clean install
```

### Configure the Database
Set up a PostgreSQL database and configure the connection properties in the `application.properties` file located in `src/main/resources/`.

Example:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/wordsworth
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
```

### Run the Application
You can run the application using the Spring Boot Maven plugin:
```sh
mvn spring-boot:run
```

## Dependencies
The main dependencies for this project as specified in the `pom.xml` file are:
- `spring-boot-starter-data-jpa`: For data access and ORM.
- `spring-boot-starter-web`: For building web applications.
- `lombok`: To reduce boilerplate code.
- `postgresql`: PostgreSQL database driver.
- `spring-boot-devtools`: For development-time features.
- `spring-boot-starter-test`: For testing.
- `spring-boot-starter-validation`: For input validation.
- `springdoc-openapi-starter-webmvc-ui`: For API documentation.
- `h2`: In-memory database for testing.

## Tools Used
Spring: For building the application.
IntelliJ IDEA Ultimate: For writing the code.
Docker: For running PostgreSQL.
Postman: For API testing.
