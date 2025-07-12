Sprint 1 Login

> Author @vinceflores

- backend for review (writing reviews, calculating avg review rating for a vehicle)
- displaying reviews as list in usedVehicle and newVehicle

### Package: src/main/java/com/\_onWheels/\_onWheels/review

Classes:

- `Review.java`: Entity class
- `ReviewController.java`: Controller for writing a review
- `ReviewDTO.java`: The data transfer object for writing a review
- `ReviewRepository.java`: Interacting with the database
- `ReviewService.java`: Service methods finding reviews by vehicle id, writing review, calculating average rating for a vehicle

HTML reusable template/fragments files:
-`review-list.html`: view fragment for listing reviews
-`review-form.html`: view fragment for writing review

Javascript files:

- `review-list.js`: For sorting the list of reviews in review-list.html

### Package: src/main/java/com/\_onWheels/\_onWheels/database

Ckasses:

- `DatabaseSeed.java`: for seeding databse to test review feature

### OThers

- `logoutbtn.html`: Reuseable logout button

Resources

- [JDBC Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html)
- [Spring Security Servelt Applications Docs](https://docs.spring.io/spring-security/reference/servlet/index.html)
- [Securing A Web Application with Spring Security](https://spring.io/guides/gs/securing-web#initial)
- [Spring Boot User Registration and Login Tutorial with MySQL Database, Bootstrap and HTML5](https://youtu.be/b8KY3BlvaFE?si=tU6V8cSe1Id2YkHk)
- [spring6yt list of Spring FrameWork Examples/Tutorials](https://github.com/navinreddy20/spring6yt)
