# 4onWheels
EECS-4413 Project

### Requirements
- Docker Engine v20+
- Docker Compose (if not bundled)
- Maven
### Install Docker 
Download and install Docker Desktop from:
https://www.docker.com/products/docker-desktop/

Download and install Maven from:
https://maven.apache.org/install.html

## Setup
From project root directory, cd into:
- ./4onWheels/4onWheels/
And run:
- ./mvnw clean package
- docker compose up --build

## Basic Usage
Visit the HomePage with limited functionality @ /HomePage

Sign in @ /login with these dev credentials:
email: ```sunny@gmail.com```
password: ```Password_2```

(See src/main/java/com/_onWheels/_onWheels/database/DatabaseSeed.java)
for full functionality.

For the admin, register and login with this superuser email:
8a8f0c7e7c12d8a4f53b4e4788a28a8f8bc9f3122c4f1236fdfc5f6c2d7f7263@gmail.com

After doing so, you will have access to: /admin/health & /admin/sales

For more exploration, see report.
