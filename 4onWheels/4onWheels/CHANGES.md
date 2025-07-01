Sprint 1 Login

> Author @vinceflores

- Merged remote branches to a local `features/auth`

```
remotes/origin/feature/login-registration-pages -----\
                                                      -> feature/auth
remotes/origin/feature/user-registration-backend ----/
```

> I had trouble with Conflicts, but switching to VSCode IDE helped.

- Configured Sping JPA (User, UserRepository) and connected to a local mysql database
- Integrated basic auth using Spring Security by creating `CustomUserDetails.java` and `CustomUserDetailsService.java`
  and adding some @Beans to SecurityConfig.java
- Added controllers to manially test login functionality using Browser and Postman
- Moved databse credentials used by `src/main/resources/applications.properties` to a .env that needs to be created.
- Created a `RegistrationController.java` class because of issues with POSt requests not being called which turned out to be related to csrf protection by Spring Security.
- Added a json file containing simple postman collection for testing login, and register. !Import to postman for use.

> I modified the register only so I can test password that uses Bycrypt encoding

Resources

- [JDBC Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html)
- [Spring Security Servelt Applications Docs](https://docs.spring.io/spring-security/reference/servlet/index.html)
- [Securing A Web Application with Spring Security](https://spring.io/guides/gs/securing-web#initial)
- [Spring Boot User Registration and Login Tutorial with MySQL Database, Bootstrap and HTML5](https://youtu.be/b8KY3BlvaFE?si=tU6V8cSe1Id2YkHk)
- [spring6yt list of Spring FrameWork Examples/Tutorials](https://github.com/navinreddy20/spring6yt)
