# 4OnWheels

# Deployed on AWS
[here](https://i9pu6wrfx5.us-east-1.awsapprunner.com/login)

# How to run

Make sure you have Docker or Docker desktop installed

<!-- ```
4onWheels/4onWheels/4onWheels
cp env-example .env
``` -->

### With Make

Update your `application.properties` to use db from `compose.yml`

```
# !!! When using Docker
spring.datasource.url=jdbc:mysql://db:3306/4onwheels
spring.datasource.username=root
spring.datasource.password=rootpassword123
```

```
make
# or
make run
```

See all the make commands at [Makefile](Makefile)

## Manual

1. Create a database called '4onwheels'

2. Configure `application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/4onwheels
spring.datasource.username=<Your Username>
spring.datasource.password=<Your Password>
```

3. Configure .env to add the GEMINI_API_KEY
```
# 4onwheels/4onwheels/.env
GEMINI_API_KEY=
```

4. Run

```
./mvnw spring-boot:run
```

# Docker Compose commands hints

Build and run services from `compose.yml`

```
docker compose up --build
```

Tear down the containers

```
docker compose down
```

Stop the containers running, without deleting the containers

```
docker compose stop
```

Restart containers without rebuilding them

```
docker compose start
```

# Resources

- [JDBC Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html)
- [Spring Security Servelt Applications Docs](https://docs.spring.io/spring-security/reference/servlet/index.html)
- [Securing A Web Application with Spring Security](https://spring.io/guides/gs/securing-web#initial)
- [Spring Boot User Registration and Login Tutorial with MySQL Database, Bootstrap and HTML5](https://youtu.be/b8KY3BlvaFE?si=tU6V8cSe1Id2YkHk)
- spring6yt[](https://github.com/navinreddy20/spring6yt)
