FROM maven:3.8.5-openjdk-11 AS builder

WORKDIR /app/sos-utils
COPY ./sos-utils ./
RUN mvn clean install

WORKDIR /app/sos-oauth
COPY ./sos-oauth ./

RUN mvn clean package -DskipTests


FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/sos-oauth/target/*.jar ./app.jar
ENTRYPOINT java -jar app.jar