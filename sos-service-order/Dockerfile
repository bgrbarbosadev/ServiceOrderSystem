FROM maven:3.8.5-openjdk-11 AS builder

WORKDIR /app/sos-utils
COPY ./sos-utils ./
RUN mvn clean install

WORKDIR /app/sos-service-order
COPY ./sos-service-order ./

RUN mvn clean install -DskipTests


FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/sos-service-order/target/*.jar ./app.jar
ENTRYPOINT java -jar app.jar
