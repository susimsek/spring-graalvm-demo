
# Project Documentation

## Versions
- **Java Version**: 21
- **Spring Boot Version**: 3.3.5
- **GraalVM Version**: 22.3.0
- **Hibernate Version**: 6.5.3.Final

## Native Build
To compile the project as a native image, use the following command:
```bash
./mvnw native:compile -Pnative
```

## Running the Application

After building the project as a native image, you can run the application with the following command:

```bash
./target/spring-graalvm-demo
```

## Default User Credentials

Below are the default credentials for accessing the application:

| Username | Password |
|----------|----------|
| admin    | password |

Use these credentials to log in to the application.


## Accessing H2 Console

Once the application is running, you can access the H2 database console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console). Use the following credentials to log in:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

This console allows you to view and manage the in-memory database tables and data.


## Testing the Authentication Endpoint

To test the authentication endpoint, you can use the following `curl` command:

```bash
curl -X POST http://localhost:8080/api/v1/auth/token \
-H "Content-Type: application/json" \
-d '{
  "username": "admin",
  "password": "password"
}'
```

