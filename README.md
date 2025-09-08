# 4onWheels
EECS-4413 Project

# Deployed on AWS
[here](https://i9pu6wrfx5.us-east-1.awsapprunner.com/login)

NOTE: Setup readme inside of `4onwheels/4onWheels`

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
Populate the vehicles database:
```docker exec -it 4onwheels-db bash```
```mysql -uroot -prootpassword123 4onwheels```

```
INSERT INTO vehicles (
    acceleration, battery_capacity, charging_time, color, description,
    make, model, price, `range`, top_speed, `type`, year, qty
) VALUES
('3.1 sec', '75 kWh', '6.5 hours', 'Red', 'High-performance electric sedan', 'Tesla', 'Model S', 89999.99, '396 miles', '200 mph', 'New', 2022, 10),
('4.8 sec', '66 kWh', '8 hours', 'Blue', 'Reliable and efficient EV', 'Nissan', 'Leaf', 31999.00, '226 miles', '98 mph', 'New', 2021, 10),
('5.2 sec', '77 kWh', '7.5 hours', 'White', 'Spacious and tech-savvy SUV', 'Ford', 'Mustang Mach-E', 45999.50, '270 miles', '124 mph', 'Used', 2021, 10),
('6.3 sec', '64 kWh', '7 hours', 'Grey', 'Compact EV with fast charging', 'Kia', 'Niro EV', 38999.00, '239 miles', '104 mph', 'New', 2023, 10),
('3.5 sec', '100 kWh', '8.5 hours', 'Black', 'Luxury SUV with Autopilot', 'Tesla', 'Model X', 109999.99, '348 miles', '155 mph', 'New', 2022, 10),
('6.9 sec', '58 kWh', '6 hours', 'Silver', 'Affordable city EV', 'Hyundai', 'Ioniq 5', 41999.00, '303 miles', '115 mph', 'Used', 2022, 10),
('4.4 sec', '90 kWh', '9 hours', 'Red', 'Sports-focused luxury EV', 'Audi', 'e-tron GT', 99999.00, '238 miles', '152 mph', 'New', 2023, 10),
('7.5 sec', '40 kWh', '5 hours', 'White', 'Entry-level compact EV', 'Chevrolet', 'Bolt EV', 27999.99, '259 miles', '93 mph', 'Used', 2020, 10),
('3.9 sec', '82 kWh', '7.25 hours', 'Blue', 'Modern EV crossover', 'Tesla', 'Model Y', 55999.00, '330 miles', '135 mph', 'New', 2023, 10),
('5.6 sec', '77 kWh', '7.75 hours', 'Green', 'Stylish EV with great range', 'Volkswagen', 'ID.4', 36999.99, '275 miles', '112 mph', 'New', 2021, 10);

```
From project root directory, cd into:
- ./4onWheels/4onWheels/
And run:
```./mvnw clean package```
```docker compose up --build```


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
