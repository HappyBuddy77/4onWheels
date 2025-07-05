
all: build upbuild

run: build up

build:
	./mvnw clean package
up:
	docker compose up
down:
	docker compose down
upbuild:
	docker compose up --build
stop:
	docker compose stop
clean:
	./mvnw clean
