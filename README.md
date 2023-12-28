# Book Fellow App Backend using Spring Boot

A Spring Boot backend for a social media platform for book lovers to share their favorite books and connect with other readers.

## Built With

- [Spring Boot 3](https://spring.io/projects/spring-boot)
- [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Gradle](https://gradle.org/)
- [PostgreSQL 15](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [JWT](https://jwt.io/)
- [Lombok](https://projectlombok.org/)
- [Liquibase](https://www.liquibase.org/)

## Development

Create an `.env` file in the root directory of the project and add the following environment variables:

```shell
SPRING_PROFILES_ACTIVE=dev
POSTGRES_DB=<database_name>
POSTGRES_USER=<database_user>
POSTGRES_PASSWORD=<database_user_password>
DEV_SERVER_PORT=8080
DEV_DATASOURCE_URL=jdbc:postgresql://localhost:5432/<database_name>
DEV_DATASOURCE_USERNAME=<database_user>
DEV_DATASOURCE_PASSWORD=<database_user_password>
JWT_SECRET=<jwt_secret_key>
```

## Getting Started Locally

1. Initialize the PostgreSQL database:

    ```shell
    docker compose up -d
    ```

2. Build and run the application:
    ```shell
    ./gradlew build
    ./gradlew bootRun
    ```

The application setup includes an initial admin user with credentials:

```text
username: admin@test.com
password: adminpassword
```

## API Quickstart

Base URL:

```
http://localhost:8080/api/v1
```

The following endpoints are available post-deployment:

- Sign Up: `/auth/signup`
- Sign In: `/auth/signin`
- Refresh Token: `/auth/refresh`
- Sample Admin Endpoint: `/admin` (requires 'ADMIN' role)
- Sample User Endpoint: `/user` (requires 'USER' role)

Use the JWT token returned by the `/auth/signin` endpoint to access the
`/admin` and `/user` endpoints.

Authenticate by providing the JWT obtained from /auth/signin. Refresh tokens can be acquired via /auth/refresh.

## Detailed Authentication Workflow

Endpoints for authentication services:

- POST `/auth/signup`: Register a new user.
    ```json
    {
    "firstName": "<firstName>",
    "lastName": "<lastName>",
    "email": "<email>",
    "password": "<password>"
    }
    ```
- POST `/auth/signin`: Login and receive an access token.
    ```json
    {
    "email": "<email>",
    "password": "<password>"
    }
    ```
  Response:
    ```json
    {
    "token": "<token>",
    "refreshToken": "<refreshToken>"
    }
    ```

- POST `/auth/refresh`: Refresh an access token.
    ```json
    {
      "token": "<refreshToken>"
    }
    ```

  Response:
    ```json
    {
    "token": "<token>",
    "refreshToken": "<refreshToken>"
    }
    ```

## Comprehensive API Reference

Access the OpenAPI documentation at:

```
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config`
```

## Testing

For unit testing, run the following command:

```shell
./gradlew test
```

For API testing, a Postman collection is provided:
[Postman Collection](https://justinegeorge96.postman.co/workspace/abcd)

Ensure that the following environment variables are set:
[Environment](https://justinegeorge96.postman.co/workspace/abcd)

Pre-request scripts are provided to automate the process of obtaining a JWT token and refreshing it.

## License

This project is licensed under the [MIT License](LICENSE).