# 4OnWheels

# How to run

Make sure you have Docker or Docker desktop installed

<!-- ```
4onWheels/4onWheels/4onWheels
cp env-example .env
``` -->

### With Make

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

3. Run

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
