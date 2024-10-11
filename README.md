# Wordsworth

## Description
Wordsworth is a full-stack web application that provides flashcard functionality, designed to help users study more effectively. The backend is built using Spring Boot, while the frontend is under development using React. This repository contains the backend code, with the frontend repository to be added soon. Once available, a link to the frontend will be provided here: _I am currently learning React.js and building the frontend_.

The Wordsworth API allows users to create, manage, and review flashcard sets, and supports CRUD operations on individual flashcards. The system is built with scalability in mind, making it a practical tool for personal study sessions or use in educational environments.

### Why Wordsworth?
Wordsworth was created to combine my passion for software development with my need for effective study tools. This project serves as both a personal learning experience in modern web technologies and a practical application that I use for studying in college. 

The name "Wordsworth" was inspired by the famous poet William Wordsworth, as I wanted a scholarly and literary reference to reflect the app's educational purpose. As an avid reader, I found this name fitting for a tool designed to help expand vocabulary and knowledge.

## Prerequisites
To run Wordsworth, you’ll need the following installed on your machine:
- Java 17 or higher
- Maven 3.6.0 or higher
- PostgreSQL (for runtime database)

## Setup Instructions

### 1. Clone the Repository
Start by cloning the repository to your local machine:
```sh
git clone https://github.com/dbronshteyn/Wordsworth.git
cd Wordsworth
```

### 2. Build the Project
Ensure that both Java and Maven are properly installed on your system. Then, build the project using Maven:
```sh
mvn clean install
```

> **Note**: If you encounter issues while building in IntelliJ, refer to [this helpful Stack Overflow guide](https://stackoverflow.com/questions/35409788/how-to-clean-or-clean-build-my-maven-project-in-intellij-idea).

### 3. Configure the Database
Set up a PostgreSQL instance on your machine and update the connection details in the `application.properties` file, located in `src/main/resources/`.

Example configuration:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/wordsworth
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
```

### 4. Run the Application
You can start the backend application using the Spring Boot Maven plugin:
```sh
mvn spring-boot:run
```

Once the application is running, you can access the API at `http://localhost:8080`.

## Dependencies
The primary dependencies used in this project (defined in the `pom.xml` file) include:
- **spring-boot-starter-data-jpa**: For ORM and data access.
- **spring-boot-starter-web**: For building the web layer of the application.
- **lombok**: To reduce boilerplate code (such as getters and setters).
- **postgresql**: PostgreSQL database driver.
- **spring-boot-devtools**: Provides live reload and other development-time features.
- **spring-boot-starter-test**: Testing framework for unit and integration tests.
- **spring-boot-starter-validation**: For validating incoming API requests.
- **springdoc-openapi-starter-webmvc-ui**: Generates API documentation via Swagger UI.
- **h2**: In-memory database for testing purposes.

## Tools Used
- **Spring Boot**: Framework for building Java-based applications.
- **PostgreSQL**: Database for persistent storage.
- **Docker**: For containerizing the PostgreSQL database.
- **IntelliJ IDEA Ultimate**: Primary IDE for development.
- **Postman**: Used for testing API endpoints.
