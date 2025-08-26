FROM maven:3.8.5-openjdk-11 AS builder

WORKDIR /app/sos-service-discovery
COPY ./sos-service-discovery ./

RUN mvn clean install -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/sos-service-discovery/target/*.jar ./app.jar
EXPOSE 8761
ENTRYPOINT java -jar app.jar