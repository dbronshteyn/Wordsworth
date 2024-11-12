# Wordsworth

## Description
Wordsworth is a full-stack web application that provides flashcard functionality, designed to help users study more effectively. The backend is built using Spring Boot, while the frontend is built using React. 

Wordsworth allows users to create, manage, and review flashcard sets, and supports CRUD operations on individual flashcards. Additionally, Wordsworth leverages the Llama 3.2 large language model to generate flashcards from a user-provided prompt. 

## 🎥 Demo

### 🎉 Introducing the New "Generate" Feature!

With the latest update, you can now **generate flashcards automatically** from any prompt you provide—powered by the Llama 3.2 large language model!

Check out the demo of the new "Generate" feature which creates flashcards from a provided prompt:

[![New Wordsworth Feature: Generate!](https://img.youtube.com/vi/wwOJUsb-IFc/0.jpg)](https://youtu.be/wwOJUsb-IFc)

Click on the image or [here](https://youtu.be/wwOJUsb-IFc) to watch the video.

<br>

### Original Wordsworth Demo!

Check out a demo of the Wordsworth frontend in action:

[![Wordsworth Demo](https://img.youtube.com/vi/X6R_3I3cwxo/0.jpg)](https://youtu.be/X6R_3I3cwxo)

Click on the image or [here](https://youtu.be/X6R_3I3cwxo) to watch the video.

### Why Wordsworth?
Wordsworth was created to combine my passion for software development with my need for effective study tools. This project serves as both a personal learning experience in modern web technologies and a practical application that I use for studying in college. 

The name "Wordsworth" was inspired by the famous poet William Wordsworth, as I wanted a scholarly and literary reference to reflect the app's educational purpose. As an avid reader, I found this name fitting for a tool designed to help expand vocabulary and knowledge.

## Prerequisites
To run Wordsworth, you’ll need the following installed on your machine:
- Java 17 or higher
- Maven 3.6.0 or higher
- Node.js 23 (for frontend)
- PostgreSQL (for runtime database)
- Ollama (to run Llama 3.2)

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

To package a JAR file:
```sh
mvn package
```

### 3. Configure the Database and Start Ollama
Set up a PostgreSQL instance on your machine and update the connection details in the `application.properties` file, located in `src/main/resources/`.

Example configuration:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/wordsworth
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
```

Install and run [Ollama](https://ollama.com/). Then download Llama 3.2.

> **Note:** If you want to use a different language model, specify the model name in the `application.properties` file.
>
> Example configuration:
> ```properties
> spring.ai.ollama.chat.model=llama3.2
> ```

### 4. Run the Application
You can start the application with:
```sh
java -jar ./target/Wordsworth-0.0.1-SNAPSHOT.jar
```

Once the application is running, you can access it at `http://localhost:8080`.

### 5. Access the API Documentation
The API documentation is generated using Swagger UI and can be accessed at `http://localhost:8080/swagger-ui.html`.

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
- **spring-ai-ollama-spring-boot-starter**: Integrating AI

## Technologies Used
- **Spring Boot**: Framework for building Java-based applications.
- **PostgreSQL**: Database for persistent storage.
- **React**: For the frontend.
- **Docker**: For containerizing the PostgreSQL database.
- **IntelliJ IDEA Ultimate**: Primary IDE for backend development.
- **WebStorm**: Primary IDE for frontend development.
- **Postman**: Used for testing API endpoints.
